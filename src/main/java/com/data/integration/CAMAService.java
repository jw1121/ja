package com.data.integration;

import com.data.integration.domain.CAMAPayload;
import org.springframework.stereotype.Service;

@Service
public class CAMAService {

    @Autowired
    CAMARepository camaRepository;

    public boolean process(CAMAPayload camaPayload) {

        camaRepository.insertOWNDAT();
        //if there is multiple own use ownmlt
        camaRepository.insertOWNMLT();

        camaRepository.insertSALE();
        return false;
    }


}
