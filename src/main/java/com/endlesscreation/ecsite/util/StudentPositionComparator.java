package com.endlesscreation.ecsite.util;

import com.endlesscreation.ecsite.domain.Position;
import com.endlesscreation.ecsite.dto.MemberHashtagResponse;

import java.util.Comparator;

public class StudentPositionComparator implements Comparator
{

    @Override
    public int compare(Object o1, Object o2)
    {
        Position position1 = ((MemberHashtagResponse) o1).getPosition();
        Position position2 = ((MemberHashtagResponse) o2).getPosition();

        return position1.compareTo(position2);
    }
}