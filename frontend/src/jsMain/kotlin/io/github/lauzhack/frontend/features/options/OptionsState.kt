package io.github.lauzhack.frontend.features.options

/** The state of the options panel. */
interface OptionsState {

  /** The mode of the options. */
  enum class Mode {

    /** The options are closed in a minimized state. */
    Closed,

    /** The options are open in an editable state. */
    Open,
  }

  /** The mode of the options. */
  val mode: Mode

  /** The value of the start time, if known. */
  val startTime: String?

  /** The input for the start time. */
  var startTimeInput: String

  /** The value of the start location, if known. */
  val locationFrom: String?

  /** The input for the start location. */
  var locationFromInput: String

  /** The value of the arrival location, if known. */
  val locationTo: String?

  /** The input for the end location. */
  var locationToInput: String

  /** The value of the user plan, if known. */
  val subscription: String?

  /** The input for the user plan. */
  var subscriptionInput: String

  /**
   * A callback which will be called when the user presses the button to toggle the mode of the
   * options.
   */
  fun onToggleModeClick()

  /** Saves the current options set by the user. */
  fun onSave()
}
