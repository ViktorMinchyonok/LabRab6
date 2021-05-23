package bsu.rfe.java.group9.lab6.Minchyonok.varA9;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
// Константы, задающие размер окна приложения, если оно
// не распахнуто на весь экран
private static final int WIDTH = 700;
private static final int HEIGHT = 500;
private JMenuItem pauseMenuItemAllBalls; 
private JMenuItem pauseMenuItem5Balls;
private JMenuItem resumeMenuItemAllBalls; 
// Поле, по которому прыгают мячи
private Field field = new Field();
// Конструктор главного окна приложения
public MainFrame() {
super("Программирование и синхронизация потоков");
setSize(WIDTH, HEIGHT);
Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
setLocation((kit.getScreenSize().width - WIDTH)/2,
(kit.getScreenSize().height - HEIGHT)/2);
// Установить начальное состояние окна развѐрнутым на весь экран

setExtendedState(MAXIMIZED_BOTH);
// Создать меню
JMenuBar menuBar = new JMenuBar();
setJMenuBar(menuBar);
JMenu ballMenu = new JMenu("Мячи");
Action addBallAction = new AbstractAction("Добавить мяч") {
public void actionPerformed(ActionEvent event) {
field.addBall();
if (!pauseMenuItem.isEnabled() &&
!resumeMenuItem.isEnabled()) {
// Ни один из пунктов меню не являются
// доступными - сделать доступным "Паузу"
pauseMenuItemAllBalls.setEnabled(true);
pauseMenuItem5Balls.setEnabled(true);
resumeMenuItemAllBalls.setEnabled(false);
}
}
};
menuBar.add(ballMenu);
ballMenu.add(addBallAction);
JMenu controlMenu = new JMenu("Управление");
menuBar.add(controlMenu);
Action pauseAction5Balls = new AbstractAction("Приостановить движение случайных 5 мячей"){
			public void actionPerformed(ActionEvent event) {
				field.pause5Balls();
				pauseMenuItemAllBalls.setEnabled(true);
				pauseMenuItem5Balls.setEnabled(false);
				resumeMenuItemAllBalls.setEnabled(true);
			}
		}; 

pauseMenuItem5Balls = controlMenu.add(pauseAction5Balls);
pauseMenuItem5Balls.setEnabled(false);

Action pauseActionAllBalls = new AbstractAction("Приостановить движение всех мячей"){
public void actionPerformed(ActionEvent event) {
field.pauseAllBalls();
pauseMenuItemAllBalls.setEnabled(false);
pauseMenuItem5Balls.setEnabled(false);
resumeMenuItemAllBalls.setEnabled(true);
			}
		};
pauseMenuItemAllBalls = controlMenu.add(pauseActionAllBalls);
pauseMenuItemAllBalls.setEnabled(false);
Action resumeActionAllBalls = new AbstractAction("Возобновить движение всех мячей") {
public void actionPerformed(ActionEvent event) {
field.resumeAllBalls();
pauseMenuItemAllBalls.setEnabled(true);
pauseMenuItem5Balls.setEnabled(true);
resumeMenuItemAllBalls.setEnabled(false);
			}
		};
resumeMenuItemAllBalls = controlMenu.add(resumeActionAllBalls);
resumeMenuItemAllBalls.setEnabled(false);
// Добавить в центр граничной компоновки поле Field
getContentPane().add(field, BorderLayout.CENTER);
}
// Главный метод приложения
public static void main(String[] args) {
// Создать и сделать видимым главное окно приложения
MainFrame frame = new MainFrame();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
}
}
