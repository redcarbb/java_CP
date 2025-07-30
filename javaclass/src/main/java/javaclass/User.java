package javaclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor//編譯時會幫你產生一個 沒有參數的建構子
@AllArgsConstructor//編譯時會幫你產生一個 包含所有欄位的建構子
@EqualsAndHashCode//單純比較兩者字元



public class User {

	@Exclude//例外這行
	private Long id;
	
	private String name;
	
}