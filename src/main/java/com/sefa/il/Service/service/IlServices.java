package com.sefa.il.Service.service;


import com.sefa.il.Service.model.Il;
import com.sefa.il.Service.repository.IlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IlServices {
    private final IlRepository ilRepository;
    public List<Il> getIller() {
        return ilRepository.findAll();
    }

    public Il createIl(Il newIl) {
        return ilRepository.save(newIl);
    }

    public void deleteIl(String id) {
        ilRepository.deleteById(id);
    }

    public Il getIlById(String id) {
       return  ilRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("il not found"));
    }

    public void updateIl(String id, Il newIl) {
        Il oldIl = getIlById(id);
        oldIl.setName(newIl.getName());
        ilRepository.save(oldIl);

    }
}
