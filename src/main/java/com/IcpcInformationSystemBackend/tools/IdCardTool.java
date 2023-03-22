package com.IcpcInformationSystemBackend.tools;

import com.IcpcInformationSystemBackend.dao.UserDoMapper;
import com.IcpcInformationSystemBackend.model.entity.UserDo;
import com.IcpcInformationSystemBackend.model.entity.UserDoExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class IdCardTool {
    @Resource
    private UserDoMapper userDoMapper;

    private final int[] idCardArr = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private final Map<Integer, Character> hashMap = new HashMap<Integer, Character>() {
        {
            put(0, '1');
            put(1, '0');
            put(2, 'X');
            put(3, '9');
            put(4, '8');
            put(5, '7');
            put(6, '6');
            put(7, '5');
            put(8, '4');
            put(9, '3');
            put(10, '2');
        }
    };
    public boolean judgeIdCardFormatIfRight(String idCard) {
        if (idCard.length() != 18)
            return false;
        int tmp = 0;
        for (int i = 0; i < 17; i++)
            tmp += idCardArr[i] * Integer.parseInt(String.valueOf(idCard.charAt(i)));
        tmp %= 11;
        return hashMap.get(tmp) == idCard.charAt(17);
    }

    public boolean judgeIdCardIfHasRegistered(String idCard) {
        UserDoExample userDoExample = new UserDoExample();
        userDoExample.createCriteria().andIdCardEqualTo(idCard);
        List<UserDo> userDos;
        userDos = userDoMapper.selectByExample(userDoExample);
        return !userDos.isEmpty();
    }
}
