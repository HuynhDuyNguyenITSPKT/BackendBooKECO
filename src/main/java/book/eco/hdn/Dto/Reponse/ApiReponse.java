package book.eco.hdn.Dto.Reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiReponse {
    // 1 is true , 0 is false , or error code
    private int code;
    private String message;
    private Object data;
}
