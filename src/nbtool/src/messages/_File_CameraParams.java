// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CameraParams.proto

package messages;

public final class _File_CameraParams {
  private _File_CameraParams() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_messages_CameraParams_descriptor;
  static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_messages_CameraParams_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022CameraParams.proto\022\010messages\"\250\002\n\014Camer" +
      "aParams\022\023\n\013whichCamera\030\001 \001(\t\022\016\n\006H_FLIP\030\002" +
      " \001(\005\022\016\n\006V_FLIP\030\003 \001(\005\022\025\n\rauto_exposure\030\004 " +
      "\001(\005\022\022\n\nbrightness\030\005 \001(\005\022\020\n\010contrast\030\006 \001(" +
      "\005\022\022\n\nsaturation\030\007 \001(\005\022\013\n\003hue\030\010 \001(\005\022\021\n\tsh" +
      "arpness\030\t \001(\005\022\r\n\005gamma\030\n \001(\005\022\030\n\020autoWhit" +
      "eBalance\030\013 \001(\005\022\020\n\010exposure\030\014 \001(\005\022\014\n\004gain" +
      "\030\r \001(\005\022\024\n\014whiteBalance\030\016 \001(\005\022\023\n\013fadeToBl" +
      "ack\030\017 \001(\005B\026B\022_File_CameraParamsP\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_messages_CameraParams_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_messages_CameraParams_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_messages_CameraParams_descriptor,
        new java.lang.String[] { "WhichCamera", "HFLIP", "VFLIP", "AutoExposure", "Brightness", "Contrast", "Saturation", "Hue", "Sharpness", "Gamma", "AutoWhiteBalance", "Exposure", "Gain", "WhiteBalance", "FadeToBlack", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
