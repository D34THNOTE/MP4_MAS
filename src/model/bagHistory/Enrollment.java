package model.bagHistory;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Enrollment {

    private static Set<Enrollment> extent = new HashSet<>();
    private Student student;

    private Course course;

    //TODO implement all these attributes
    private LocalDate startDate, endDate;

    private Integer finalGrade;


    public Enrollment(Student student, Course course, LocalDate startDate, LocalDate endDate) {
        if(student == null || course == null) throw new IllegalArgumentException("At least one of the arguments for Enrollment was null");

        this.student = student;
        this.course = course;
        setStartDate(startDate);
        setEndDate(endDate);

        addEnrollment(this);
    }

    public static Set<Enrollment> getExtent() {
        return Collections.unmodifiableSet(extent);
    }

    public static void addEnrollment(Enrollment enrollment) {
        if(enrollment == null) throw new IllegalArgumentException("Enrollment to be added cannot be empty");
        verifyDatesOfEnrollment(enrollment); // verifying the same relation won't have a start/end date colliding with an already existing relation

        extent.add(enrollment);

        if(!enrollment.student.getEnrollments().contains(enrollment)) {
            enrollment.student.addEnrollmentStudent(enrollment);
        }
        if(!enrollment.course.getEnrollments().contains(enrollment)) {
            enrollment.course.addEnrollmentCourse(enrollment);
        }
    }

    public void remove() {
        // removing references
        if(student.getEnrollments().contains(this)) student.removeEnrollmentStudent(this);
        if(course.getEnrollments().contains(this)) course.removeEnrollmentCourse(this);

        // removing from extent if references have been removed
        if(!student.getEnrollments().contains(this) && !course.getEnrollments().contains(this)) {
            extent.remove(this);
        } else {
            throw new RuntimeException("There was an error while removing enrollment from extent!");
        }
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    private void setStartDate(LocalDate startDate) {
        if(startDate == null) throw new IllegalArgumentException("Start date is required");

        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    private void setEndDate(LocalDate endDate) {
        if(endDate == null) throw new IllegalArgumentException("End date is required");
        if(endDate.isBefore(startDate)) throw new IllegalArgumentException("End date has to be a date after start day of the course");

        this.endDate = endDate;
    }

    public Integer getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Integer finalGrade) {
        if(finalGrade != null && (finalGrade.compareTo(2) < 0 || finalGrade.compareTo(5) > 0))
            throw new IllegalArgumentException("Final grade can only be an integer between 2 and 5");

        this.finalGrade = finalGrade;
    }



    private static void verifyDatesOfEnrollment(Enrollment enrollment) {
        for(Enrollment possibleCollision : extent) {
            if(enrollment.student.equals(possibleCollision.student) && enrollment.course.equals(possibleCollision.course)) {
                if(enrollment.startDate.isAfter(possibleCollision.startDate) || enrollment.startDate.isBefore(possibleCollision.endDate))
                    throw new IllegalArgumentException("Start date of this enrollment collides with an already existing enrollment");

                if(enrollment.endDate.isAfter(possibleCollision.startDate) || enrollment.endDate.isBefore(possibleCollision.endDate))
                    throw new IllegalArgumentException("End date of this enrollment collides with an already existing enrollment");
            }
        }
    }
}

