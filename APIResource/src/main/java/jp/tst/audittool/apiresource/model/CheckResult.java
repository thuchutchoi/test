package jp.tst.audittool.apiresource.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class CheckResult.
 */
@Getter
@Setter
public class CheckResult {

	/** The Constant MSG_OK. */
	public static final String MSG_OK = "OK";

	/** The Constant MSG_HEADER_NG. */
	public static final String MSG_HEADER_NG = "ERROR(HTTP Header NG)";

	/** The success. */
	private boolean success;

	/** The message. */
	private String message;

	/**
	 * Instantiates a new check result.
	 *
	 * @param success
	 *            the success
	 * @param message
	 *            the message
	 */
	public CheckResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	/**
	 * Instantiates a new check result.
	 */
	public CheckResult() {

	}
}
