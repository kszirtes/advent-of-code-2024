package aoc2024.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day03 {
    public static void main(String[] args) {

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of("inputs/day03.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(lines.size());
        System.out.println(method1("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"));
        System.out.println(lines.stream().mapToLong(Day03::method1).sum());
        System.out.println(method2("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"));

        System.out.println(lines.stream().mapToLong(Day03::method2).sum());

        StringBuilder sb = new StringBuilder();
        lines.forEach(sb::append);
        System.out.println(method2(sb.toString()));


    }

    private static long method1(String text) {
        String[] muls = text.split("mul");
        long multiply = 0;
        for (String mul : muls) {
            if (!mul.isEmpty() && mul.charAt(0) == '(') {
                String[] split1 = mul.split(",");
                if (split1.length >= 2) {
                    long a = 0, b = 0;
                    try {
                        a = Integer.parseInt(split1[0].substring(1));
                    } catch (NumberFormatException e) {
                        System.out.print("nan, ");
                    }

                    if (split1[1].length() >= 4 && split1[1].charAt(3) == ')' && split1[1].charAt(2) != ')' && split1[1].charAt(1) != ')') {
                        try {
                            b = Integer.parseInt(split1[1].substring(0, 3));
                        } catch (NumberFormatException e) {
                            System.out.print("nan, ");
                        }
                    } else if (split1[1].length() >= 3 && split1[1].charAt(2) == ')' && split1[1].charAt(1) != ')') {
                        try {
                            b = Integer.parseInt(split1[1].substring(0, 2));
                        } catch (NumberFormatException e) {
                            System.out.print("nan, ");
                        }
                    } else if (split1[1].length() >= 2 && split1[1].charAt(1) == ')') {
                        try {
                            b = Integer.parseInt(split1[1].substring(0, 1));
                        } catch (NumberFormatException e) {
                            System.out.print("nan, ");
                        }
                    }

                    if (a != 0 && b != 0) {
                        multiply = multiply + (a * b);
                    }
                }
            }
        }
        return multiply;
    }

    /*182619527
      182619815
       24650529
       32321083
       29286457
       31832172
       29722781
       34806505
    */

    private static long method2(String text) {
        String[] muls = text.split("mul");
        long multiply = 0;
        boolean doIt = true;
        for (String mul : muls) {
            if (!mul.isEmpty()) {
                if (mul.charAt(0) == '(') {
                    String[] split1 = mul.split(",");
                    if (split1.length >= 2) {
                        long a = 0, b = 0;
                        try {
                            a = Integer.parseInt(split1[0].substring(1));
                        } catch (NumberFormatException e) {
                            System.out.print("nan, ");
                        }

                        if (split1[1].length() >= 4 && split1[1].charAt(3) == ')' && split1[1].charAt(2) != ')' && split1[1].charAt(1) != ')') {
                            try {
                                b = Integer.parseInt(split1[1].substring(0, 3));
                            } catch (NumberFormatException e) {
                                System.out.print("nan, ");
                            }
                        } else if (split1[1].length() >= 3 && split1[1].charAt(2) == ')' && split1[1].charAt(1) != ')') {
                            try {
                                b = Integer.parseInt(split1[1].substring(0, 2));
                            } catch (NumberFormatException e) {
                                System.out.print("nan, ");
                            }
                        } else if (split1[1].length() >= 2 && split1[1].charAt(1) == ')') {
                            try {
                                b = Integer.parseInt(split1[1].substring(0, 1));
                            } catch (NumberFormatException e) {
                                System.out.print("nan, ");
                            }
                        }

                        if (a != 0 && b != 0 && doIt) {
                            multiply = multiply + (a * b);
                        }
                    }
                }
                if (mul.contains("don't()")){
                    doIt = false;
                }
                if (mul.contains("do()")){
                    doIt = true;
                }
            }
        }
        return multiply;
    }
}
