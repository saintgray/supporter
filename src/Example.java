import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static supporter.IterableSupporter.filterCollection;
import static supporter.IterableSupporter.flattenToCollection;
import static supporter.IterableSupporter.groupAndCollect;
import static supporter.IterableSupporter.mergeCollection;
import static supporter.IterableSupporter.remapCollection;
import static supporter.IterableSupporter.removeInCollection;
import static supporter.IterableSupporter.sortedCollection;
import static supporter.MapSupporter.extract;
import static supporter.MapSupporter.inject;

public class Example {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Random gradeGenerator = new Random();
        Random classGenerator = new Random();

        Collection<Student> list1 = new ArrayList<>();
        Collection<Student> list2 = new ArrayList<>();
        list1.add(new Student("서울초등학교", "김아무개", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "김아무개1", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "김아무개2", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "김아무개3", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "김아무개4", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "김아무개5", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "김아무개6", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "김아무개7", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "최아무개1", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "최아무개2", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "최아무개3", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "최아무개4", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "최아무개5", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "최아무개6", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "김아무개111", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "김아무개4444", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "ㅇㅇㅇ", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "이름1", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list1.add(new Student("서울초등학교", "이름2", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "김말숙", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "김똥개", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "최무무", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "아무무", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "원펀맨", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "도라에몽", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "피카츄", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "아무이름", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "오징어", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "세균맨", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "호빵맨", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "ㅇㅇㅇ", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "undefined", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "아들", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "내이름", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "개발자", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "운영자", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "PM", generate(gradeGenerator, 1, 6), generate(classGenerator, 1, 12)));
        list2.add(new Student("판교초등학교", "오종현", 6, 1));

        // 1. 두개 학교를 학생들을 모두 병합
        Collection<Student> allStudents = mergeCollection(list1, list2);
        // 2. 모든 학생을 학교명으로 그룹핑
        Map<String, List<Student>> group = groupAndCollect(allStudents, Student::getSchoolName, Collectors.toList());
        // 3. 모든 학생을 학교명, 학년, 반, 이름 Map 으로 변환 (이름에 중복이 없다고 가정)
        Map<String, Map<Integer, Map<Integer, Map<String, Student>>>> studentMap = groupAndCollect(
                allStudents,
                Student::getSchoolName,
                Student::getGrade,
                Student::getClassNo,
                Collectors.toMap(Student::getName, Function.identity())
        );
        // 4. map 에서 특정 data 추출
        Student student = extract(studentMap, "판교초등학교", 6, 1, "오종현"); //expect non null
        Student student2 = extract(studentMap, "없는초등학교", 6, 1, "오종현"); // expect null;
        // 4-1. 서울초등학교 2학년 학생 Map 추출
        Map<Integer, Map<String, Student>> seoulClassStudentMap = extract(studentMap, "서울초등학교", 2);
        // 4-2. 서울초 2학년 학생을 모두 추출
        Collection<Student> seoulSecondGradeStudents = flattenToCollection(extract(studentMap, "서울초등학교", 2), ArrayList::new);
        // 5. map 에 특정 data 주입
        Student newStudent = new Student("새로운초등학교", "김똥개", 1, 6);
        inject(newStudent, studentMap, newStudent.getSchoolName(), newStudent.getGrade(), newStudent.getClassNo(), newStudent.getName());
        // 6. 1학년인 학생만 모두 추출
        Collection<Student> firstGradeStudents = filterCollection(allStudents, s -> s.getGrade() == 1);
        // 7. 서울초등학교 6학년인 학생만 추출
        Collection<Student> seoulSixGradeStudents = filterCollection(
                allStudents,
                s -> s.getGrade() == 6,
                s -> "서울초등학교".equals(s.schoolName)
        );
        // 8. 3학년인 학생 모두 삭제
        allStudents = removeInCollection(allStudents, ArrayList::new, s -> s.getGrade() == 3);
        // 9. 학교명, 학년, 반 순서로 정렬
        allStudents = sortedCollection(
                allStudents,
                Comparator.comparing(Student::getSchoolName),
                Comparator.comparing(Student::getGrade),
                Comparator.comparing(Student::getClassNo)
        );
        // 10. nested map 객체를 List 로 펼침
        Collection<Student> flatten = flattenToCollection(studentMap, ArrayList::new);
        // 11. 모든 학생의 이름을 추출
        Collection<String> names = remapCollection(allStudents, Student::getName);

        Collection<String> test = Arrays.asList("1", "2");
        test = removeInCollection(test, ArrayList::new, "2"::equals);

    }


    public static int generate(Random generator, int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }


    public static class Student {
        private String schoolName;
        private String name;
        private Integer grade; // 학년
        private Integer classNo;

        public String getSchoolName() {
            return this.schoolName;
        }

        public String getName() {
            return this.name;
        }

        public Integer getGrade() {
            return this.grade;
        }

        public Integer getClassNo() {
            return this.classNo;
        }

        public Student(String schoolName, String name, int grade, int classNo) {
            this.schoolName = schoolName;
            this.name = name;
            this.grade = grade;
            this.classNo = classNo;
        }
    }
}
