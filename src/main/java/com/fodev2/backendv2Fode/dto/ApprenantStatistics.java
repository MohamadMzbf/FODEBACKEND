package com.fodev2.backendv2Fode.dto;

import java.util.List;

public class ApprenantStatistics {
    private String city;
    private String country;
    private String profileimageurl;
    private List<CustomField> customfields;
    private List<Role> roles;
    private List<EnrolledCourse> enrolledcourses;

    // Getter and Setter methods for the fields

    public static class CustomField {
        private String type;
        private String value;
        private String name;
        private String shortname;

        // Getter and Setter methods for the fields
    }

    public static class Role {
        private Long roleid;
        private String name;
        private String shortname;
        private int sortorder;

        // Getter and Setter methods for the fields
    }

    public static class EnrolledCourse {
        private Long id;
        private String fullname;
        private String shortname;

        // Getter and Setter methods for the fields
    }

}
