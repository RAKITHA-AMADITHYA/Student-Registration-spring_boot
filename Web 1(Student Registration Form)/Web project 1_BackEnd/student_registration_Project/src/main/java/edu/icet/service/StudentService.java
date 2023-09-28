package edu.icet.service;

import edu.icet.dto.StudentDTO;
import edu.icet.dao.Student;
import edu.icet.repo.StudentRepo;
import edu.icet.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ModelMapper modelMapper;


    public String saveStudent(StudentDTO studentDTO){
        if (studentRepo.existsById(studentDTO.getStdId())){
            return VarList.RSP_DUPLICATED;
        }else {
            studentRepo.save(modelMapper.map(studentDTO, Student.class));
            return VarList.RSP_SUCCESS;
        }

    }
   public String updateStudent(StudentDTO studentDTO) {
       if (studentRepo.existsById(studentDTO.getStdId())) {
           studentRepo.save(modelMapper.map(studentDTO, Student.class));
           return VarList.RSP_SUCCESS;
       } else {
           return VarList.RSP_NO_DATA_FOUND;
       }
   }

   public List<StudentDTO>getAllStudents(){
            List<Student>stdList=studentRepo.findAll();
            return  modelMapper.map(stdList,new TypeToken<ArrayList< StudentDTO>>(){
       }.getType());
   }
   public StudentDTO searchStudent(int stdId){
        if (studentRepo.existsById(stdId)){
            Student student=studentRepo.findById(stdId).orElse(null);
            return modelMapper.map(student, StudentDTO.class);
        }else {
            return null;
        }
   }
   public String deleteStudent(int stdId){
        if (studentRepo.existsById(stdId)){
            studentRepo.deleteById(stdId);
            return  VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }

   }


}


