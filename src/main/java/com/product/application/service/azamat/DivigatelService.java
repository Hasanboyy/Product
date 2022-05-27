package com.product.application.service.azamat;

import com.product.application.dto.azamat.DivigatelDto;
import com.product.application.filter.azamat.DivigatelFilter;
import com.product.application.repository.azamat.DivigatelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class DivigatelService {
    private final DivigatelRepository divigatelRepository;

    public boolean create(DivigatelDto divigatelDto) {
        return false;
    }

    public DivigatelDto get(Integer id) {
        return null;
    }

    public boolean update(Integer id, DivigatelDto divigatelDto) {
        return false;
    }

    public boolean delete(Integer id) {
        return false;
    }

    public List<DivigatelDto> findAllByPage(Integer page, Integer size) {
        return null;
    }

    public List<DivigatelDto> filter(DivigatelFilter divigatelFilter) {
        return null;
    }
}
