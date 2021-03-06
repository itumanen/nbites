cmake_minimum_required(VERSION 2.8.3)

# Project name.
project(localization)

# Set source files here that need to be compiled.
set(LOCALIZATION_SRCS 
              LocalizationModule
              ParticleFilter
		      Particle
		      MotionModel
		      SensorModel
		      VisionSystem
		      MotionSystem
		      LineSystem
              LandmarkSystem
)

# Add project as a library.
add_library(
  localization
  STATIC
  ${LOCALIZATION_SRCS}
)

# Link project to appropriate libraries.
target_link_libraries(localization
  vision
  support
  portals
  messages)

if ( OFFLINE )
  nbites_add_test(LocalizationTest LocalizationTest.cpp localization)
endif ( OFFLINE )
