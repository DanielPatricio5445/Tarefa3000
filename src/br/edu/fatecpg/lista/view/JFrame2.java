package br.edu.fatecpg.lista.view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.fatecpg.lista.model.Aluno;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrame2 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txt_nome;
    private JTextField txt_telefone;
    private JComboBox<Aluno> comboBox;
    private static ArrayList<Aluno> listaAlunos = new ArrayList<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame2 frame = new JFrame2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public JFrame2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbl_nome = new JLabel("Nome:");
        lbl_nome.setBounds(58, 27, 70, 15);
        contentPane.add(lbl_nome);

        txt_nome = new JTextField();
        txt_nome.setToolTipText("Nome do Aluno");
        txt_nome.setBounds(58, 47, 355, 19);
        contentPane.add(txt_nome);
        txt_nome.setColumns(10);

        JLabel lbl_telefone = new JLabel("Telefone:");
        lbl_telefone.setBounds(58, 78, 70, 15);
        contentPane.add(lbl_telefone);

        txt_telefone = new JTextField();
        txt_telefone.setToolTipText("Telefone do Aluno");
        txt_telefone.setBounds(58, 98, 355, 19);
        contentPane.add(txt_telefone);
        txt_telefone.setColumns(10);

        // Combobox para selecionar aluno
        comboBox = new JComboBox<>();
        comboBox.setBounds(58, 130, 355, 24);
        contentPane.add(comboBox);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Aluno alunoSelecionado = (Aluno) comboBox.getSelectedItem();
                if (alunoSelecionado != null) {
                    txt_nome.setText(alunoSelecionado.getNome());
                    txt_telefone.setText(alunoSelecionado.getTelefone());
                }
            }
        });

        // Botão para cadastrar
        JButton btn_cadastrar = new JButton("Cadastrar");
        btn_cadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = txt_nome.getText();
                String telefone = txt_telefone.getText();

                if (!nome.isEmpty() && !telefone.isEmpty()) {
                    Aluno aluno = new Aluno(nome, telefone);
                    listaAlunos.add(aluno);
                    comboBox.addItem(aluno);
                    JOptionPane.showMessageDialog(btn_cadastrar, "Cadastro realizado com sucesso!");
                    txt_nome.setText("");
                    txt_telefone.setText("");
                } else {
                    JOptionPane.showMessageDialog(btn_cadastrar, "Por favor, preencha todos os campos.");
                }
            }
        });
        btn_cadastrar.setBounds(58, 160, 170, 25);
        contentPane.add(btn_cadastrar);

        // Botão de Atualizar
        JButton btn_atualizar = new JButton("Atualizar");
        btn_atualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Aluno alunoSelecionado = (Aluno) comboBox.getSelectedItem();
                if (alunoSelecionado != null) {
                    String novoNome = txt_nome.getText();
                    String novoTelefone = txt_telefone.getText();
                    
                    if (!novoNome.isEmpty() && !novoTelefone.isEmpty()) {
                        alunoSelecionado.setNome(novoNome);
                        alunoSelecionado.setTelefone(novoTelefone);
                        JOptionPane.showMessageDialog(btn_atualizar, "Aluno atualizado com sucesso!");
                        
                        // Atualiza o comboBox
                        comboBox.repaint();
                        txt_nome.setText("");
                        txt_telefone.setText("");
                    } else {
                        JOptionPane.showMessageDialog(btn_atualizar, "Por favor, preencha todos os campos.");
                    }
                }
            }
        });
        btn_atualizar.setBounds(230, 160, 170, 25);
        contentPane.add(btn_atualizar);

        // Botão de Excluir
        JButton btn_excluir = new JButton("Excluir");
        btn_excluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Aluno alunoSelecionado = (Aluno) comboBox.getSelectedItem();
                if (alunoSelecionado != null) {
                    int confirmacao = JOptionPane.showConfirmDialog(btn_excluir, "Deseja realmente excluir o aluno?");
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        listaAlunos.remove(alunoSelecionado);
                        comboBox.removeItem(alunoSelecionado);
                        JOptionPane.showMessageDialog(btn_excluir, "Aluno excluído com sucesso!");
                        
                        txt_nome.setText("");
                        txt_telefone.setText("");
                    }
                }
            }
        });
        btn_excluir.setBounds(58, 200, 170, 25);
        contentPane.add(btn_excluir);

        // Botão Listar (já existente)
        JButton btn_listar = new JButton("Listar");
        btn_listar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame1 frame1 = new JFrame1(listaAlunos);
                frame1.setVisible(true);
            }
        });
        btn_listar.setBounds(230, 200, 170, 25);
        contentPane.add(btn_listar);
    }
}

