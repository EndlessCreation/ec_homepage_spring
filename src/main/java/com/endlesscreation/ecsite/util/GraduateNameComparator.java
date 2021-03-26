package com.endlesscreation.ecsite.util;

import com.endlesscreation.ecsite.dto.MemberResponse;

import java.util.Comparator;

public class GraduateNameComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        String name1 = ((MemberResponse) o1).getName();
        String name2 = ((MemberResponse) o2).getName();

        return name1.compareTo(name2);
    }
}