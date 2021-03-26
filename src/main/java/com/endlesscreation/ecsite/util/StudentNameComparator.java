package com.endlesscreation.ecsite.util;

import com.endlesscreation.ecsite.dto.MemberHashtagResponse;

import java.util.Comparator;

public class StudentNameComparator implements Comparator
{

    @Override
    public int compare(Object o1, Object o2)
    {
        String name1 = ((MemberHashtagResponse) o1).getName();
        String name2 = ((MemberHashtagResponse) o2).getName();

        return name1.compareTo(name2);
    }
}