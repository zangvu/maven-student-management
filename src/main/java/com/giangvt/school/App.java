package com.giangvt.school;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String option, id, name;
        float maths, physics, english;

        // init a student list to manage
        StudentList studentList = new StudentList(new ArrayList<Student>());

        do {
            System.out.println("STUDENT MANAGEMENT");
            System.out.println("1. Add student");
            System.out.println("2. Show student list");
            System.out.println("3. Find a student who has largest GPA");
            System.out.println("4. Find a student who has lowest GPA");
            System.out.println("0. End task");
            option = sc.nextLine();

            switch (option) {
                case "1": {
                    // input student id
                    System.out.print("Input student id: ");
                    boolean isIdDuplicatedOrEmpty = true;
                    do {
                        id = sc.nextLine();
                        if (id.isEmpty()) {
                            System.out.print("Please input valid id value: ");
                        } else if (studentList.findStudentById(id) != null) {
                            System.out.print("Duplicated id please input id again: ");
                        } else {
                            isIdDuplicatedOrEmpty = false;
                        }
                    } while (isIdDuplicatedOrEmpty);

                    // input student name
                    System.out.print("Input student name: ");
                    boolean isNameValid = false;
                    do {
                        name = sc.nextLine();
                        if (id.isEmpty()) {
                            System.out.print("Please input valid name value: ");
                        } else {
                            isNameValid = true;
                        }
                    } while (!isNameValid);

                    // input student maths mark
                    System.out.print("Input student maths mark: ");
                    maths = inputFloatNum(sc);

                    // input student physics mark
                    System.out.print("Input student physics mark: ");
                    physics = inputFloatNum(sc);

                    // input student english mark
                    System.out.print("Input student english mark: ");
                    english = inputFloatNum(sc);

                    studentList.addStudent(new Student(id, name, maths, physics, english));
                    break;
                }
                case "2": {
                    System.out.println("STUDENT LIST");
                    if (studentList.getStudentList().isEmpty()) {
                        System.out.println("Empty list!");
                    } else {
                        printTableTitle();
                        for (Student student :
                                studentList.getStudentList()) {
                            student.printProfile();
                        }
                    }
                    break;
                }
                case "3": {
                    System.out.println("Largest GPA student: ");
                    Student largestStudent = studentList.getXestGPAStudent(true);
                    if (largestStudent == null) {
                        System.out.println("Empty list!");
                    } else {
                        printTableTitle();
                        largestStudent.printProfile();
                    }
                    break;
                }
                case "4": {
                    System.out.println("Lowest GPA student: ");
                    Student lowestStudent = studentList.getXestGPAStudent(false);
                    if (lowestStudent == null) {
                        System.out.println("Empty list!");
                    } else {
                        printTableTitle();
                        lowestStudent.printProfile();
                    }
                    break;
                }
                default:
                    break;
            }

        } while (!option.equals("0"));
    }

    public static float inputFloatNum(Scanner sc) {
        float result = -1;
        boolean isValid = false;
        do {
            try {
                result = Float.parseFloat(sc.nextLine());
                if (result >= 0 && result <= 10) {
                    isValid = true;
                } else {
                    System.out.print("Please input mark between 0 and 10: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Please input valid number format: ");
                isValid = false;
            }
        } while (!isValid);
        return result;
    }

    public static void printTableTitle() {
        System.out.printf("|%8s|%-10s|%s|%s|%s|%4s|\n", "id", "name", "maths", "physics", "english", "GPA");
    }
}
