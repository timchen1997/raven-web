package com.dynamicheart.raven.services.house;

import com.dynamicheart.raven.model.house.House;
import com.dynamicheart.raven.model.member.Member;
import com.dynamicheart.raven.model.user.User;
import com.dynamicheart.raven.repositories.house.HouseRepository;
import com.dynamicheart.raven.repositories.member.MemberRepository;
import com.dynamicheart.raven.services.generic.RavenEntityServiceImpl;
import com.dynamicheart.raven.utils.exception.ServiceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class HouseServiceImpl extends RavenEntityServiceImpl<String, House>
        implements HouseService {

    private HouseRepository houseRepository;

    private MemberRepository memberRepository;

    @Inject
    public HouseServiceImpl(HouseRepository houseRepository) {
        super(houseRepository);
        this.houseRepository = houseRepository;
    }

    @Override
    public House create(House house, User founder) throws ServiceException{
        house.setFounderId(founder.getId());
        return create(house);
    }
}