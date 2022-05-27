package com.product.application.service.azamat;

import com.product.application.dto.azamat.BrendDto;
import com.product.application.filter.azamat.BrendFilter;
import com.product.application.repository.azamat.BrendRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrendService {

    private final BrendRepository brendRepository;
    public boolean create(BrendDto brendDto) {
        return false;
    }

    public BrendDto get(Integer id) {
        return null;
    }

    public boolean update(Integer id, BrendDto brendDto) {
        return false;
    }

    public boolean delete(Integer id) {
        return false;
    }

    public List<BrendDto> findAllByPage(Integer page, Integer size) {
        return null;
    }

    public List<BrendDto> filter(BrendFilter brendFilter) {
        return null;
    }
}
