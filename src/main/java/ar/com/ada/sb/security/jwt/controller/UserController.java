package ar.com.ada.sb.security.jwt.controller;

import ar.com.ada.sb.security.jwt.model.dto.UserDto;
import ar.com.ada.sb.security.jwt.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    @Qualifier("userServices")
    private UserServices userServices;

    @GetMapping({"","/"})
    public ResponseEntity getAllUsers() {
        List<UserDto> all = userServices.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping({"/{id}","/{id}/"})
    public ResponseEntity getUserById(@PathVariable Long id){
        UserDto userById = userServices.findUserById(id);
        return ResponseEntity.ok(userById);
    }

    @PostMapping({"","/"})
    public ResponseEntity addNewUser(@Valid @RequestBody UserDto userDto) {
        UserDto userSaved = userServices.save(userDto);
        return ResponseEntity.ok(userSaved);
    }

    @PutMapping({"/{id}","/{id}/"})
    public ResponseEntity updateUserById(@Valid @RequestBody UserDto userDto, @PathVariable Long id){
        UserDto userUpdated = userServices.updateUser(userDto, id);
        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping({"/{id}","/{id}/"})
    public ResponseEntity deleteUser(@PathVariable Long id){
        userServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
