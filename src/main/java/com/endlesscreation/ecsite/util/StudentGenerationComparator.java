package com.endlesscreation.ecsite.util;

import com.endlesscreation.ecsite.dto.MemberHashtagResponse;

import java.util.Comparator;

public class StudentGenerationComparator implements Comparator
{

    @Override
    public int compare(Object o1, Object o2)
    {
        Integer generation1 = ((MemberHashtagResponse) o1).getGeneration();
        Integer generation2 = ((MemberHashtagResponse) o2).getGeneration();

        return generation2.compareTo(generation1);
    }
}