package TimerProject;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TitanTimerConsoleVer {

    public static int tenCount;
    public static int tempoCount;
    public static int pauseCount;
    public static int restTimeCount;

    public static void main(String[] args) throws InterruptedException {

        //목표. 운동 1종목을 Set(S), Reps(R), RestTime(RT), Tempo(T), Pause(P), 5개의 기능을 통해 마무리 하기.

        //1. 셋트 수(Set)를 입력해주세요. **완료**
        //1-1. 셋트 수(Set) 입력(5개의 기능을 총 몇번 반복할것인지 결정) **완료**
        //2. 수행 횟수(Reps)를 입력해주세요. **완료**
        //3. 쉬는 시간(RestTime)을 입력해주세요.(분단위로 못받고 초단위로 받는걸로 수정) **완료**
        //4. 템포(Tempo)를 입력해주세요. (초 단위로 입력 ex) 5T(5초), 사운드 기능 추가) 사운드 기능 미구현
        //5. 퍼즈(Pause)를 입력해주세요. (초 단위로 입력 ex) 2P(2초), 사운드 기능 추가) 사운드 기능 미구현

        //6. 실행 하시겠습니까? 실행 or 재설정 을 입력해주세요. **완료**
        //6-1. 재설정 입력 시 원하는 설정으로 다시 설정 할 수 있게 하기(1.Set 2.Reps 3.RestTime 4.Tempo 5.Pause) **완료**
        //7. 각 세트 시작전에 언렉 후 준비시간으로 10초 카운트 사운드 기능 필요.( ex) 쉬는시간 30초중 마지막 20초 후 10초는 카운트) **완료**

        //8. 실행 선택 시 1회(Reps)로직 예시는 하기와 같다. (각 기능별 사운드 종류는 텍스트로 우선 표기) (예시는 3S / 10R / 1분30초RT / 5초T / 2초P)
        //8-1. 10초 카운트 사운드 (띠 X 9회) 10초일대는 템포 시작의 0초와 같으므로 9초까지만 카운트 (띠 X 9) **완료**
        //8-2. 5초 템포 시작. 8-1에서 0초(삑) 으로 시작 1초(띠) + 2초(띠) + 3초(띠) + 4초(띠) + 5초(띠) (삑 띠 띠 띠 띠 띠) 사운드 기능 미구현
        //8-3. 2초 퍼즈 시작 8-2에서 5초(띠) = 퍼즈 시작 0초 + 1초(띠) + 2초(띠) (띠 띠 띠) 사운드 기능 미구현
        //8-4. 리프팅을 1초로 가정하고 다음 횟수(Reps) 시작할때 0초부터 시작해야 하므로 0초를 의미하면서 다음 횟수(Reps) 시작을 의미하는 사운드 실행 (삑) 사운드 기능 미구현
        //8-5. 10회(Reps) 종료때까지 8-2 ~ 8-4 과정 반복 **완료**
        //8-6. 1Set 종료 후 쉬는시간 시작 **완료**
        //8-7. 쉬는시간 종료 10초 전 8-1 부터 시작하여 8-5까지 반복 **완료**
        //8-8. 마지막 셋트 후 6 으로 돌아가서 실행 or 재설정 선택 후 반복 **완료**

        /**타이머 셋팅 입력**/
        Scanner sc = new Scanner(System.in);

        System.out.println("1. 셋트 수를 입력해주세요.");
        int set = sc.nextInt();
        System.out.println("2. 수행 횟수를 입력해주세요.");
        int reps = sc.nextInt();
        System.out.println("3. 휴식 시간을 초단위로 입력해주세요.");
        int restTime = sc.nextInt();
        System.out.println("4. 템포를 입력해주세요.");
        int tempo = sc.nextInt();
        System.out.println("5. 퍼즈를 입력해주세요.");
        int pause = sc.nextInt();

        /**실행 혹은 재설정**/
        runOrReset(sc, set, reps, restTime, tempo, pause);
//
    }

    private static void runOrReset(Scanner sc, int set, int reps, int restTime, int tempo, int pause) throws InterruptedException {
        while (true) {
            System.out.println("6. 실행 하시겠습니까? 실행 or 재설정을 입력해주세요.");
            String runOrReset = sc.next();

            String run = "실행";
            String Reset = "재설정";

            if (run.equals(runOrReset)) {
                timerRun(set, reps, restTime, tempo, pause);
                break;
            } else if (Reset.equals(runOrReset)) {
                System.out.println("재설정을 원하는 옵션을 택하세요." + "\n1.세트 수 2.수행 횟수 3.휴식 시간 4.템포 5.퍼즈");
                int resetOption = sc.nextInt();

                switch (resetOption) {
                    case 1:
                        System.out.println("1. 세트 수를 수정해주세요.");
                        set = sc.nextInt();
                        break;
                    case 2:
                        System.out.println("2. 수행 횟수를 수정해주세요.");
                        reps = sc.nextInt();
                        break;
                    case 3:
                        System.out.println("3. 휴식 시간을 수정해주세요.");
                        restTime = sc.nextInt();
                        break;
                    case 4:
                        System.out.println("4. 템포를 수정해주세요.");
                        tempo = sc.nextInt();
                        break;
                    case 5:
                        System.out.println("5. 퍼즈를 수정해주세요.");
                        pause = sc.nextInt();
                        break;
                }

            }
        }
    }

    private static void timerRun(int set, int reps, int restTime, int tempo, int pause) throws InterruptedException {
        for (int i = 0; i < set; i++) {
            if (i == 0) {
                tenCount = 10;

                Timer countTimer = new Timer();
                TimerTask countTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (tenCount > 0) {
                            System.out.println(tenCount + "초 남았습니다.");
                            CountSound.countSound();
                            tenCount--;
                        } else {
                            countTimer.cancel();
                        }
                    }
                };
                countTimer.schedule(countTask, 0, 1000);
                Thread.sleep(10000);
            } else if (i > 0) {
                restTimeCount = restTime;

                Timer restTimer = new Timer();
                TimerTask restTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (restTimeCount > 10) {
                            System.out.println(restTimeCount);
                            restTimeCount--;
                        } else if (0 < restTimeCount && restTimeCount < 11) {
                            System.out.println(restTimeCount + "초 남았습니다.");
                            CountSound.countSound();
                            restTimeCount--;
                        } else {
                            restTimer.cancel();
                        }
                    }
                };
                restTimer.schedule(restTask, 0, 1000);
                Thread.sleep(restTime * 1000);
            }
            for (int j = 0; j < reps; j++) {
                if (tenCount == 0) {
                    tempoCount = 0;

                    Timer tempoTimer = new Timer();
                    TimerTask tempoTask = new TimerTask() {
                        @Override
                        public void run() {
                            if (tempoCount == 0) {
                                System.out.println("삑(시작)");
                                StartSound.startSound();
                                tempoCount++;
                            } else if (0 < tempoCount && tempoCount < tempo + 1) {
                                System.out.println("띠(Tempo)");
                                TempoPauseSound.tempoPauseSound();
                                tempoCount++;
                            } else {
                                tempoTimer.cancel();
                            }
                        }
                    };
                    tempoTimer.schedule(tempoTask, 0, 1000);
                    Thread.sleep((tempo + 1) * 1000);
                }

                if (tempoCount == tempo + 1) {
                    pauseCount = 0;

                    Timer pauseTimer = new Timer();
                    TimerTask pauseTask = new TimerTask() {
                        @Override
                        public void run() {
                            if (pauseCount < pause) {
                                System.out.println("띠(Pause)");
                                TempoPauseSound.tempoPauseSound();
                                pauseCount++;
                            } else {
                                pauseTimer.cancel();
                            }
                        }
                    };
                    pauseTimer.schedule(pauseTask, 0, 1000);
                    Thread.sleep(pause * 1000);
                }
            }
        }
    }
}

