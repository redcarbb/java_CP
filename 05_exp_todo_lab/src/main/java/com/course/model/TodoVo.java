package com.course.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TodoVo {

	/** 鍵值 */
	private Long id;
	
	/** 標題 */
	@NotBlank
	private String title;
	
	/** 到期日 */
	@NotBlank(message = "你給我去填到期日啊啊啊啊")
	@NotNull(message = "你給我去填到期日啊啊啊啊")
	private String dueDate;
	
	/** 狀態 */
	private String status;
	
	/** 狀態呈現 */
	private String statusDisp;
	
	/** 備註 */
	private String memo;
	
	/** 使用者名稱 */
	private String username;
	
	/** 檔案 */
	private MultipartFile file;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getStatusDisp() {
		return statusDisp;
	}

	public void setStatusDisp(String statusDisp) {
		this.statusDisp = statusDisp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "TodoVo [title=" + title + ", dueDate=" + dueDate + ", status=" + status + ", statusDisp=" + statusDisp
				+ ", memo=" + memo + "]";
	}
}
