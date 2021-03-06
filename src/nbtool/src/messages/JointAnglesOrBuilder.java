// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PMotion.proto

package messages;

public interface JointAnglesOrBuilder extends
    // @@protoc_insertion_point(interface_extends:messages.JointAngles)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional float head_yaw = 1;</code>
   *
   * <pre>
   * Head angles.
   * </pre>
   */
  boolean hasHeadYaw();
  /**
   * <code>optional float head_yaw = 1;</code>
   *
   * <pre>
   * Head angles.
   * </pre>
   */
  float getHeadYaw();

  /**
   * <code>optional float head_pitch = 2;</code>
   */
  boolean hasHeadPitch();
  /**
   * <code>optional float head_pitch = 2;</code>
   */
  float getHeadPitch();

  /**
   * <code>optional float l_shoulder_pitch = 3;</code>
   *
   * <pre>
   * Left arm angles.
   * </pre>
   */
  boolean hasLShoulderPitch();
  /**
   * <code>optional float l_shoulder_pitch = 3;</code>
   *
   * <pre>
   * Left arm angles.
   * </pre>
   */
  float getLShoulderPitch();

  /**
   * <code>optional float l_shoulder_roll = 4;</code>
   */
  boolean hasLShoulderRoll();
  /**
   * <code>optional float l_shoulder_roll = 4;</code>
   */
  float getLShoulderRoll();

  /**
   * <code>optional float l_elbow_yaw = 5;</code>
   */
  boolean hasLElbowYaw();
  /**
   * <code>optional float l_elbow_yaw = 5;</code>
   */
  float getLElbowYaw();

  /**
   * <code>optional float l_elbow_roll = 6;</code>
   */
  boolean hasLElbowRoll();
  /**
   * <code>optional float l_elbow_roll = 6;</code>
   */
  float getLElbowRoll();

  /**
   * <code>optional float l_wrist_yaw = 7;</code>
   */
  boolean hasLWristYaw();
  /**
   * <code>optional float l_wrist_yaw = 7;</code>
   */
  float getLWristYaw();

  /**
   * <code>optional float l_hand = 8;</code>
   */
  boolean hasLHand();
  /**
   * <code>optional float l_hand = 8;</code>
   */
  float getLHand();

  /**
   * <code>optional float r_shoulder_pitch = 9;</code>
   *
   * <pre>
   * Right arm angles.
   * </pre>
   */
  boolean hasRShoulderPitch();
  /**
   * <code>optional float r_shoulder_pitch = 9;</code>
   *
   * <pre>
   * Right arm angles.
   * </pre>
   */
  float getRShoulderPitch();

  /**
   * <code>optional float r_shoulder_roll = 10;</code>
   */
  boolean hasRShoulderRoll();
  /**
   * <code>optional float r_shoulder_roll = 10;</code>
   */
  float getRShoulderRoll();

  /**
   * <code>optional float r_elbow_yaw = 11;</code>
   */
  boolean hasRElbowYaw();
  /**
   * <code>optional float r_elbow_yaw = 11;</code>
   */
  float getRElbowYaw();

  /**
   * <code>optional float r_elbow_roll = 12;</code>
   */
  boolean hasRElbowRoll();
  /**
   * <code>optional float r_elbow_roll = 12;</code>
   */
  float getRElbowRoll();

  /**
   * <code>optional float r_wrist_yaw = 13;</code>
   */
  boolean hasRWristYaw();
  /**
   * <code>optional float r_wrist_yaw = 13;</code>
   */
  float getRWristYaw();

  /**
   * <code>optional float r_hand = 14;</code>
   */
  boolean hasRHand();
  /**
   * <code>optional float r_hand = 14;</code>
   */
  float getRHand();

  /**
   * <code>optional float l_hip_yaw_pitch = 15;</code>
   *
   * <pre>
   * Pelvis angles.
   * </pre>
   */
  boolean hasLHipYawPitch();
  /**
   * <code>optional float l_hip_yaw_pitch = 15;</code>
   *
   * <pre>
   * Pelvis angles.
   * </pre>
   */
  float getLHipYawPitch();

  /**
   * <code>optional float r_hip_yaw_pitch = 16;</code>
   */
  boolean hasRHipYawPitch();
  /**
   * <code>optional float r_hip_yaw_pitch = 16;</code>
   */
  float getRHipYawPitch();

  /**
   * <code>optional float l_hip_roll = 17;</code>
   *
   * <pre>
   * Left leg angles.
   * </pre>
   */
  boolean hasLHipRoll();
  /**
   * <code>optional float l_hip_roll = 17;</code>
   *
   * <pre>
   * Left leg angles.
   * </pre>
   */
  float getLHipRoll();

  /**
   * <code>optional float l_hip_pitch = 18;</code>
   */
  boolean hasLHipPitch();
  /**
   * <code>optional float l_hip_pitch = 18;</code>
   */
  float getLHipPitch();

  /**
   * <code>optional float l_knee_pitch = 19;</code>
   */
  boolean hasLKneePitch();
  /**
   * <code>optional float l_knee_pitch = 19;</code>
   */
  float getLKneePitch();

  /**
   * <code>optional float l_ankle_pitch = 20;</code>
   */
  boolean hasLAnklePitch();
  /**
   * <code>optional float l_ankle_pitch = 20;</code>
   */
  float getLAnklePitch();

  /**
   * <code>optional float l_ankle_roll = 21;</code>
   */
  boolean hasLAnkleRoll();
  /**
   * <code>optional float l_ankle_roll = 21;</code>
   */
  float getLAnkleRoll();

  /**
   * <code>optional float r_hip_roll = 22;</code>
   *
   * <pre>
   * Right leg angles.
   * </pre>
   */
  boolean hasRHipRoll();
  /**
   * <code>optional float r_hip_roll = 22;</code>
   *
   * <pre>
   * Right leg angles.
   * </pre>
   */
  float getRHipRoll();

  /**
   * <code>optional float r_hip_pitch = 23;</code>
   */
  boolean hasRHipPitch();
  /**
   * <code>optional float r_hip_pitch = 23;</code>
   */
  float getRHipPitch();

  /**
   * <code>optional float r_knee_pitch = 24;</code>
   */
  boolean hasRKneePitch();
  /**
   * <code>optional float r_knee_pitch = 24;</code>
   */
  float getRKneePitch();

  /**
   * <code>optional float r_ankle_pitch = 25;</code>
   */
  boolean hasRAnklePitch();
  /**
   * <code>optional float r_ankle_pitch = 25;</code>
   */
  float getRAnklePitch();

  /**
   * <code>optional float r_ankle_roll = 26;</code>
   */
  boolean hasRAnkleRoll();
  /**
   * <code>optional float r_ankle_roll = 26;</code>
   */
  float getRAnkleRoll();
}
