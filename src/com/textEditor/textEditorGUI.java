package com.textEditor;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import com.textEditor.Decorator_Design_Pattern.ColorDecorator;
import com.textEditor.Decorator_Design_Pattern.DefaultTheme;
import com.textEditor.Decorator_Design_Pattern.FontFamilyDecorator;
import com.textEditor.Decorator_Design_Pattern.FontSizeDecorator;
import com.textEditor.Strategy_Design_Pattern.StrategyFileMethods;
import com.textEditor.Strategy_Design_Pattern.SaveAs;
import com.textEditor.Strategy_Design_Pattern.New;
import com.textEditor.Strategy_Design_Pattern.Exit;
import com.textEditor.Strategy_Design_Pattern.Open;
import com.textEditor.Strategy_Design_Pattern.Save;
import com.textEditor.Factory_Design_Pattern.Format;
import com.textEditor.Factory_Design_Pattern.Factory;
import com.textEditor.Command_Design_Pattern.UndoImplement;
import com.textEditor.Command_Design_Pattern.UndoFunction;
import com.textEditor.Command_Design_Pattern.UndoCommand;

public class textEditorGUI extends JFrame{

    private textEditorGUI() {
        initComponents();
    }

    // Singleton Design Pattern form nesnesi için oluşturuldu.
    private static textEditorGUI textEditorGUI ;

    public static textEditorGUI getTextEditorGUI (){
        if(textEditorGUI == null){
            textEditorGUI = new textEditorGUI();
        }
        return textEditorGUI;
    };
    

    // Command tasarim deseniyle undo islemi icin gerekli nesneler olusturuldu.

    final UndoFunction undo_function_object = new UndoFunction(this);
    final UndoCommand undo_command_object = new UndoCommand(undo_function_object);
    final UndoImplement undo_implement_object = new UndoImplement(undo_command_object);

    // Factory design pattern icin uygun nesneler olusturuldu.

    final Factory find_replace_factory = new Factory();

    Format format_object1 = find_replace_factory.getFormat("find");
    Format format_object2 = find_replace_factory.getFormat("replace");
    Format format_object3 = find_replace_factory.getFormat("replace all");
    Format format_object4 = find_replace_factory.getFormat("find all");


    // Strategy design pattern icin uygun nesneler olusturuldu.

    StrategyFileMethods openFileMethod = new StrategyFileMethods(new Open());
    StrategyFileMethods newFileMethod = new StrategyFileMethods(new New());
    StrategyFileMethods saveFileMethod = new StrategyFileMethods(new Save());
    StrategyFileMethods saveAsMethod = new StrategyFileMethods(new SaveAs());
    StrategyFileMethods exitFileMethod = new StrategyFileMethods(new Exit());

    private String openText;
    private String fileAddress;

    //VARIABLE
    public JPanel editPanel;
    public JFrame window;
    public JTextArea textArea;
    public JScrollPane scrollPane;

    //TOP MENU BAR
    public JMenuBar menuBar;
    public JMenu menuFile, menuEdit;
    // FILE MENU
    public JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    // EDIT MENU
    public JMenuItem iUndo;
    public JLabel findLabel;
    public JLabel replaceLabel;
    public JTextField FindTextField;
    public JTextField ReplaceTextField;
    public JButton findButton;
    public JButton replaceButton;
    public JButton replaceAllButton;
    public JButton findAllButton;
    public JComboBox comboBoxFontSize;
    public JComboBox comboBoxFontFamily;
    public JComboBox comboBoxFontColor;
    public JLabel fontSizeLabel;
    public JLabel fontFamilyLabel;
    public JLabel fontColorLabel;
    public JButton editButton;

    // VARIABLE END...

    UndoManager undoManager = new UndoManager();

    private void initComponents() {

        createPanel();
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createTopPanel();

        window.setVisible(true);
        window.setResizable(false); // form cannot resize.
        window.setSize(800,600); // setted width and height
        window.setLocationRelativeTo(null);  // app will be display in the center of screen
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void createPanel(){
        editPanel = new javax.swing.JPanel();
    }
    public void createWindow(){ window = new JFrame("Notepad"); }
    public void createTextArea(){
        textArea = new JTextArea();
        textArea.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
                    @Override
                    public void undoableEditHappened(UndoableEditEvent e) {
                        undoManager.addEdit(e.getEdit());
                    }
                }
        );
        textArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textAreaKeyPressed(evt);
            }
        });

        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setWrapStyleWord(true);
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // scroll bar
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);

    }
    public void createMenuBar(){
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);
        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

    }
    public void createFileMenu(){
        iNew = new JMenuItem("New");
        iNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFileActionPerformed(evt);
            }
        });

        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });

        iSave = new JMenuItem("Save");
        iSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileActionPerformed(evt);
            }
        });

        iSaveAs = new JMenuItem("Save As");
        iSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAsActionPerformed(evt);
            }
        });

        iExit = new JMenuItem("Exit");
        iExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        menuFile.add(iNew);
        menuFile.add(iOpen);
        menuFile.add(iSave);
        menuFile.add(iSaveAs);
        menuFile.add(iExit);
    }
    public void createEditMenu(){
       iUndo = new JMenuItem("Undo") ;
        iUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UndoActionPerformed(evt);
            }
        });
        menuEdit.add(iUndo);
    }
    public void createTopPanel(){

        findLabel = new JLabel();
        replaceLabel = new JLabel();
        FindTextField = new JTextField();
        ReplaceTextField = new JTextField();
        findButton = new JButton();
        replaceButton = new JButton();
        replaceAllButton = new JButton();
        findAllButton = new JButton();
        fontSizeLabel = new JLabel();
        fontFamilyLabel = new JLabel();
        fontColorLabel = new JLabel();
        editButton = new JButton();

        comboBoxFontSize = new JComboBox();
        comboBoxFontFamily = new JComboBox();
        comboBoxFontColor =  new JComboBox();


        fontSizeLabel.setText("Font Size");
        fontFamilyLabel.setText("Font Family");
        fontColorLabel.setText("Font Color");
        findLabel.setText("Find: ");
        replaceLabel.setText("Replace: ");
        findButton.setText("Find");
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindButtonActionPerformed(evt);
            }
        });

        findAllButton.setText("Find All");
        findAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindAllActionPerformed(evt);
            }
        });

        replaceButton.setText("Replace");
        replaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReplaceButtonActionPerformed(evt);
            }
        });

        replaceAllButton.setText("Replace All");
        replaceAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReplaceAllActionPerformed(evt);
            }
        });

        GroupLayout jPanelLayout = new GroupLayout(editPanel);
        editPanel.setLayout(jPanelLayout);

        comboBoxFontSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8", "16", "20", "24", "28" }));
        comboBoxFontColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Black", "White" ,"Green", "Red", "Blue" }));
        comboBoxFontFamily.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arial", "Comic Sans MS", "Times New Roman"}));

        JLabel jLabelSize = new JLabel();
        JLabel jLabelColor = new JLabel();
        JLabel jLabelFamily = new JLabel();
        jLabelSize.setText("Size");
        jLabelColor.setText("Color");
        jLabelFamily.setText("Family");

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editStyleActionPerformed(evt);
            }
        });

        jPanelLayout.setHorizontalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanelLayout.createSequentialGroup()
                                                .addComponent(replaceLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ReplaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelLayout.createSequentialGroup()
                                                .addComponent(findLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(FindTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(findButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(replaceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(replaceAllButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(findAllButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelLayout.createSequentialGroup()
                                                .addComponent(comboBoxFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(comboBoxFontColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelLayout.createSequentialGroup()
                                                .addComponent(jLabelSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabelColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelLayout.createSequentialGroup()
                                                .addComponent(jLabelFamily, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(90, 90, 90))
                                        .addGroup(jPanelLayout.createSequentialGroup()
                                                .addComponent(comboBoxFontFamily, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(editButton)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                        .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanelLayout.setVerticalGroup(
                jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(findLabel)
                                        .addComponent(findButton)
                                        .addComponent(FindTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(findAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelSize)
                                        .addComponent(jLabelColor)
                                        .addComponent(jLabelFamily))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(replaceLabel)
                                        .addComponent(ReplaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(replaceButton)
                                        .addComponent(replaceAllButton)
                                        .addComponent(comboBoxFontColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboBoxFontSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboBoxFontFamily, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(editButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        window.add(editPanel);

    }


    // EVENTS
    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {
        openFileMethod.execute(this);

    }

    private void newFileActionPerformed(java.awt.event.ActionEvent evt) {
        newFileMethod.execute(this);
    }

    private void saveFileActionPerformed(java.awt.event.ActionEvent evt) {
        saveFileMethod.execute(this);
    }

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {
        exitFileMethod.execute(this);
    }

    private void FindButtonActionPerformed(java.awt.event.ActionEvent evt) {
        format_object1.execute(this);
    }

    private void ReplaceButtonActionPerformed(java.awt.event.ActionEvent evt) {
        format_object2.execute(this);
    }

    private void ReplaceAllActionPerformed(java.awt.event.ActionEvent evt) {
        format_object3.execute(this);
    }

    private void FindAllActionPerformed(java.awt.event.ActionEvent evt) {
        format_object4.execute(this);
    }

    private void UndoActionPerformed(java.awt.event.ActionEvent evt) {
        undo_implement_object.pop();
    }

    // Text Area ' ya veri girisi yapıldığı andan itibaren stack push edilmeye baslandı.
    private void textAreaKeyPressed(java.awt.event.KeyEvent evt) {
        undo_implement_object.push();
    }

    private void SaveAsActionPerformed(java.awt.event.ActionEvent evt) {
        saveAsMethod.execute(this);
    }

    private void editStyleActionPerformed(java.awt.event.ActionEvent evt) {
        // Decorator Design Pattern
        DefaultTheme defaultTheme = new DefaultTheme(this);
        FontSizeDecorator fontSize = new FontSizeDecorator(defaultTheme);

        fontSize.setStyle(comboBoxFontSize.getSelectedItem().toString());

        ColorDecorator colorPattern = new ColorDecorator(fontSize);

        colorPattern.setStyle( comboBoxFontColor.getSelectedItem().toString());

        FontFamilyDecorator fontFamily = new FontFamilyDecorator(colorPattern);

        fontFamily.setStyle( comboBoxFontFamily.getSelectedItem().toString());

    }

    public String getOpenText() {
        return openText;
    }

    public void setOpenText(String text) {
        this.openText = text;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }
}