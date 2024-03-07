package in.zkteco.util;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorInfo {

	private String message;
	private LocalDateTime when;
}
