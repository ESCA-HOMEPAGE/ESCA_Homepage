package com.esca.escahp.keeper;

import com.esca.escahp.keeper.repository.KeeperRepository;
import org.springframework.stereotype.Service;

@Service
public class KeeperService implements I_KeeperService{
    private final KeeperRepository keeperRepository;

    public KeeperService(KeeperRepository keeperRepository) {
        this.keeperRepository = keeperRepository;
    }
}