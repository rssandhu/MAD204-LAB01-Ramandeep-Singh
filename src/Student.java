/**
 * MAD204-01 Lab 1
 * Author: Ramandeep Singh
 * Student ID: A00194321
 * Date: 14/09/2025
 * Description:
 *  The Student class represents a student with a name, ID, and five grades.
 *  It includes methods for setting grades, computing averages, determining letter grades,
 *  and generating a summary string.
 */

public class Student {
    public String name;
    public int id;
    public double[] grades = new double[5];

    /**
     * Sets a grade value at the specified index (0..4)
     * @param index the grade position (0 to 4)
     * @param value the grade to set
     */
    public void setGrade(int index, double value) {
        if (index >= 0 && index < grades.length) {
            grades[index] = value;
        }
    }

    /**
     * Calculates the average of the 5 grades.
     * @return average as double
     */
    public double average() {
        double sum = 0;
        for (double g : grades) sum += g;
        return sum / grades.length;
    }

    /**
     * Returns letter grade based on the average.
     * @return 'A', 'B', 'C', 'D', or 'F'
     */
    public char letterGrade() {
        double avg = average();
        if (avg >= 90) return 'A';
        else if (avg >= 80) return 'B';
        else if (avg >= 70) return 'C';
        else if (avg >= 60) return 'D';
        else return 'F';
    }

    /**
     * Returns a formatted string with the student's info.
     * @return formatted details string
     */
    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Avg: %.1f (%c)", id, name, average(), letterGrade());
    }
}
