package com.sefa.il.Service.controller;

import com.sefa.il.Service.exception.IlAlreadyExistsException;
import com.sefa.il.Service.exception.IlNotFoundException;
import com.sefa.il.Service.model.Il;
import com.sefa.il.Service.service.IlServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/iller")
@AllArgsConstructor
public class ILController {


    private final IlServices ilService;


@GetMapping
    public ResponseEntity<List<Il>> getIller(@RequestParam(required = false) String name){
        return new ResponseEntity<>(ilService.getIller(name), OK);
    }
@GetMapping("/{id}")
    public ResponseEntity<Il> getIl(@PathVariable String id){

        return new ResponseEntity<>(getIlById(id), OK);
}

    private Il getIlById(String id) {
    return ilService.getIlById(id);
}

    @PostMapping
    public ResponseEntity<Il> createIl(@RequestBody Il newIl){

        return new ResponseEntity<>(ilService.createIl(newIl),CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void>getIl(@PathVariable String id,@RequestBody Il newIl){
    Il oldIl = getIlById(id);
    oldIl.setName(newIl.getName());
    oldIl.setCreateDate(new Date());
    ilService.updateIl(id,newIl);

    return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIl(@PathVariable String id){
    ilService.deleteIl(id);
    return new ResponseEntity<>(OK);
    }
@ExceptionHandler(IlNotFoundException.class)
    public ResponseEntity<String> handleIlNotFoundException(IlNotFoundException ex){
return new ResponseEntity<>(ex.getMessage(),NOT_FOUND);
    }

    @ExceptionHandler(IlAlreadyExistsException.class)
    public ResponseEntity<String> handleIlAlreadyExistsException(IlAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(),CONFLICT);
    }

}