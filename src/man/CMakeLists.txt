project( man )
cmake_minimum_required( VERSION 2.8.3 )

# Make sure we can find the shared files
set( SHARE_DIR "${NBITES_DIR}/src/share" )
# So that we can use find_package commands
set( CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} "${SHARE_DIR}/cmake/" )

# A list of options that we do want
include( cmake/RobotConfig.cmake )
# A list of options for debugging various modules
include( ${SHARE_DIR}/cmake/DebugConfig.cmake)
# A list of options that should be advanced
include( cmake/Advanced.cmake )
# Compiler flags
include( cmake/Flags.cmake )

# Configure the robot options
configure_file(
  "${CMAKE_CURRENT_LIST_DIR}/cmake/parameters.in"
  "${PROJECT_BINARY_DIR}/install/lib/parameters.json"
)

# Configure the debug options
configure_file(
  "${SHARE_DIR}/cmake/DebugConfig.in"
  "${PROJECT_BINARY_DIR}/DebugConfig.h"
)

# Configure the copy script based on our settings
configure_file(
  "${CMAKE_CURRENT_LIST_DIR}/cmake/upload.sh.in"
  "${PROJECT_BINARY_DIR}/upload.sh"
  @ONLY # So cmake doesn't complaing about using ${VAR} in bash
)

if (NOT CMAKE_BUILD_TYPE)
  message(STATUS "No build type selected, default to RelWithDebInfo")
  set(CMAKE_BUILD_TYPE "RelWithDebInfo")
endif()

include_directories( ${PROJECT_BINARY_DIR} )

# Option to turn rebuilding our protobuf libs on/off
option( BUILD_PROTOBUF "Rebuild the protobuf libraries." OFF )
if( BUILD_PROTOBUF )
  # Only build them if the user wants to
  include( ${SHARE_DIR}/cmake/BuildProtobuf.cmake )
endif()

# testing set up
if ( OFFLINE )
  include ( ${SHARE_DIR}/cmake/BuildGTest.cmake )
endif()

# For Aldebaran's headers (actually just for the cross-compiled boost lib)
find_package(ALCOMMON REQUIRED)
include_directories( ${ALCOMMON_INCLUDE_DIR} )

find_package( PROTOBUF REQUIRED )
include_directories( SYSTEM ${PROTOBUF_INCLUDE_DIR} )

# For Python
find_package( PythonLibs REQUIRED)
include_directories( ${PYTHON_INCLUDE_DIR} )

set(Boost_NO_BOOST_CMAKE true)
find_package(Boost COMPONENTS python REQUIRED)
include_directories( ${Boost_INCLUDE_DIR} )

# Our core includes come from here
include_directories( ${SHARE_DIR}/include )

# Compile portals, which is outside the source tree so specify a destination
add_subdirectory( ${SHARE_DIR}/portals ${CMAKE_CURRENT_BINARY_DIR}/portals/ )
# Make portals available to everything
include_directories( ${SHARE_DIR}/portals )

# Compile logshare
add_subdirectory( ${SHARE_DIR}/logshare ${CMAKE_CURRENT_BINARY_DIR}/logshare/ )
# Make logshare available to everything
include_directories( ${SHARE_DIR}/logshare )

# Also messages
add_subdirectory( ${SHARE_DIR}/messages ${CMAKE_CURRENT_BINARY_DIR}/messages/ )
# And make sure generated pb.h files are visible
include_directories( ${CMAKE_CURRENT_BINARY_DIR}/messages/ )
include_directories( ${SHARE_DIR}/messages )

# If protobuf libs don't exist yet, make sure they build before man
if( BUILD_PROTOBUF )
add_dependencies(messages protobuf_libs)
endif()

# Actual subdirectories
include_directories( support )
add_subdirectory( support )
include_directories( motion/bhwalk ) # @todo hack?

# Actual modules
add_subdirectory( arms )
add_subdirectory( audio )
add_subdirectory( balltrack )
add_subdirectory( behaviors )
add_subdirectory( comm )
add_subdirectory( control )
add_subdirectory( gamestate )
add_subdirectory( guardian )
add_subdirectory( image )
add_subdirectory( jointenactor )
#add_subdirectory( led )
add_subdirectory( localization )
add_subdirectory( log )
add_subdirectory( motion )
add_subdirectory( obstacle )
add_subdirectory( sensors )
add_subdirectory( sharedball )
add_subdirectory( naiveball )
add_subdirectory( vision )

set( MAN_SRCS main.cpp
  Man.cpp
  DiagramThread.cpp
  ParamReader.cpp
)

add_executable(
  man
  ${MAN_SRCS}
)

target_link_libraries(
  man
  arms
  audio
  balltrack
  behaviors
  bhwalk
  comm
  control
  gamestate
  guardian
  image
  jointenactor
  #led
  localization
  log
  messages
  motion
  obstacle
  portals
  logshare
  sensors
  sharedball
  naiveball
  support
  vision
)

install( TARGETS man
  RUNTIME DESTINATION lib
)

file( GLOB configFiles
  "config/*"
)

install( FILES ${configFiles}
  DESTINATION ${CMAKE_INSTALL_PREFIX}/Config )
