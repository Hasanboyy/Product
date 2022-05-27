package com.product.application.service.azamat;

import com.product.application.dto.azamat.MerchandDto;
import com.product.application.repository.azamat.MarchentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class MenrchantService {

    private final MarchentRepository marchentRepository;
    public boolean create(MerchandDto merchandDto) {
        return false;
    }

    public MerchandDto get(Integer id) {
        return null;
    }

    public boolean update(Integer id, MerchandDto merchandDto) {
        return false;
    }

    public boolean delete(Integer id) {
        return false;
    }

    public List<MerchandDto> findAllByPage(Integer page, Integer size) {
        return null;
    }

    public List<MerchandDto> filter(MerchandDto merchandDto) {
        return null;
    }
}
