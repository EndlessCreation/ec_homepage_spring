package com.endlesscreation.ecsite.util;

import com.endlesscreation.ecsite.dto.MemberResponse;

import java.util.Comparator;

public class GraduateGenerationComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Integer generation1 = ((MemberResponse) o1).getGeneration();
        Integer generation2 = ((MemberResponse) o2).getGeneration();

        return generation2.compareTo(generation1);
    }
}
