package ar.com.ada.sb.security.jwt.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Serializable {

    private Long id;

    @NotBlank( message = "name is required")
    private String name;

}
