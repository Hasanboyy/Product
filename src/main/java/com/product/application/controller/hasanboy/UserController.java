package com.product.application.controller.hasanboy;

import com.product.application.dto.hasanboy.UserDto;
import com.product.application.service.hasanboy.UserService;
import com.product.application.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid UserDto dto){
        boolean result = userService.create(dto);
        return ResponseEntity.ok(result);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id){
        UserDto result = userService.get(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid UserDto dto){
        boolean result = userService.update(id,dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        boolean result = userService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/info")
    public ResponseEntity<?> getInfo(){
        Integer userId = SecurityUtil.getUserId();
        UserDto result = userService.get(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/admin/list")
    public ResponseEntity<?> getAllForAdmin(@RequestParam("s") Integer size,
                                            @RequestParam("p") Integer page){
        List<UserDto> result = userService.getAllForAdmin(size,page);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/create-admin/{id}")
    public ResponseEntity<?> changeUserToAdmin(@PathVariable("id") Integer id){
        boolean result = userService.changeUserToAdmin(id);
        return ResponseEntity.ok(result);
    }

}
