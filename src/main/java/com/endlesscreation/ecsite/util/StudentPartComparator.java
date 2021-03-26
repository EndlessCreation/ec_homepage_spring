package com.endlesscreation.ecsite.util;

import com.endlesscreation.ecsite.domain.Position;
import com.endlesscreation.ecsite.dto.MemberHashtagResponse;

import java.util.Comparator;

public class StudentPartComparator implements Comparator
{

    @Override
    public int compare(Object o1, Object o2)
    {
        Boolean part1 = ((MemberHashtagResponse) o1).getPart();
        Boolean part2 = ((MemberHashtagResponse) o2).getPart();

        return Boolean.compare(part2, part1);
    }
}