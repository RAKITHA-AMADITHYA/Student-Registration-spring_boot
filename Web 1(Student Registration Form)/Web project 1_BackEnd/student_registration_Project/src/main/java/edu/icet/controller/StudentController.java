package edu.icet.controller;

import edu.icet.dto.ResponseDTO;
import edu.icet.dto.StudentDTO;
import edu.icet.service.StudentService;
import edu.icet.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "/save")
    public ResponseEntity saveStudent(@RequestBody StudentDTO studentDTO) {
       try {
        String res = studentService.saveStudent(studentDTO);
        if (res.equals("00")) {
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Student Adding Successfully");
            responseDTO.setContent(String.valueOf(studentDTO));
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);

        } else if (res.equals("06")) {
            responseDTO.setCode(VarList.RSP_DUPLICATED);
            responseDTO.setMessage("Student registered");
            responseDTO.setContent(String.valueOf(studentDTO));
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        } else {
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }


    }catch(Exception ex){
        responseDTO.setCode(VarList.RSP_ERROR);
        responseDTO.setMessage(ex.getMessage());
        responseDTO.setContent(null);
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@PutMapping(value = "/update")
public ResponseEntity updateStudent(@RequestBody StudentDTO studentDTO){
    try {
        String res = studentService.updateStudent(studentDTO);
        if (res.equals("00")) {
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage(" Update Success!..");
            responseDTO.setContent(String.valueOf(studentDTO));
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);

        } else if (res.equals("01")) {
            responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
            responseDTO.setMessage("Student registered");
            responseDTO.setContent(String.valueOf(studentDTO));
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        } else {
            responseDTO.setCode(VarList.RSP_FAIL);
            responseDTO.setMessage("Error");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }


    }catch(Exception ex){
        responseDTO.setCode(VarList.RSP_ERROR);
        responseDTO.setMessage(ex.getMessage());
        responseDTO.setContent(null);
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
@GetMapping("/getAll")
public ResponseEntity getAllStudents(){
    try {
        List<StudentDTO> stdDTOList = studentService.getAllStudents();
        responseDTO.setCode(VarList.RSP_SUCCESS);
        responseDTO.setMessage("Successfully Displayed");
        responseDTO.setContent(String.valueOf(stdDTOList));
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }catch (Exception ex){
        responseDTO.setCode(VarList.RSP_ERROR);
        responseDTO.setMessage(ex.getMessage());
        responseDTO.setContent(null);
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@GetMapping("/serch/{stdId}")
public ResponseEntity searchStudent(@PathVariable int stdId){
        StudentDTO studentDTO=studentService.searchStudent(stdId);
    try {
        if (studentDTO !=null){
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Search Success");
            responseDTO.setContent(String.valueOf(studentDTO));
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);

        }else {
            responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
            responseDTO.setMessage("No Student Available For this Student");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }catch (Exception ex){
        responseDTO.setCode(VarList.RSP_ERROR);
        responseDTO.setMessage(ex.getMessage());
        responseDTO.setContent(null);
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    @DeleteMapping("/delete/{stdId}")
    public ResponseEntity deleteStudent(@PathVariable int stdId) {
        String response=studentService.deleteStudent(stdId);
        try {
            if (response.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully Deleted");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);

            }else {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No Student Available For this StudentId");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
