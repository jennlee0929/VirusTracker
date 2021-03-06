package ui;

import controller.VirusDatabase;
import javafx.util.Pair;
import model.*;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseDialog extends JFrame {
    private static VirusDatabase virusDatabase = null;
    private SearchOutput searchOutput = null;

    public JPanel databaseDialog;
    //Place Table
    private JLabel place;
    private JTextField nameTextFieldFromPlace;
    private JTextField houseNumTextFieldFromPlace;
    private JTextField streetNameTextFieldFromPlace;
    private JTextField postalCodeTextFieldFromPlace;
    private JTextField cnameTextFieldFromPlace;
    private JButton showPlaceTable;
    private JButton submitPlaceData;
    private JSpinner lowerboundDateSpinnerForSearchPlace;
    private JSpinner upperboundDateSpinnerForSearchPlace;
    private JButton searchPlaceByDateButton;

    //Country Table
    private JLabel country;
    private JButton showCountryTable;

    //Person Table
    private JButton showPersonTable;
    private JTextField nationalityField_Person;
    private JTextField visitedRouteField_Person;
    private JSpinner startingAtField_Person;
    private JSpinner endingAtField_Person;
    private JButton searchPerson;
    private JTextField sinumField_Person;
    private JButton updateRouteButton;
    private JTextField startedAfterField_Person;
    private JButton searchVirus;
    private JSpinner startedAfterField_Virus;
    private JButton SearchNotinfectedPeopleWhoWentToDangerousRouteButton;
    private JButton showRoutesButton;
    private JTextField nationality_Person;
    private JButton showCuresButton;
    private JTextField deleteRouteTextField;
    private JButton deleteRouteButton;
    private JButton showRouteButton;
    private JTextField joinNationalityField;
    private JTextField joinSinumField;
    private JButton showRouteIDCountButton;
    private JButton showNationalityCountButton;
    private JButton searchCureButton;
    private JButton showNotInfectedButton;

    public DatabaseDialog(VirusDatabase virusDatabase)  {

        this.virusDatabase = virusDatabase;

        //set the Jspinner for searching place in give date
        //set up lowerboundDateSpinnerForSearchPlace
        SpinnerDateModel model = new SpinnerDateModel();
        lowerboundDateSpinnerForSearchPlace.setModel(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(lowerboundDateSpinnerForSearchPlace, "MM/dd/yyyy");
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        lowerboundDateSpinnerForSearchPlace.setEditor(editor);
        //set up upperboundDateSpinnerForSearchPlace
        SpinnerDateModel model2 = new SpinnerDateModel();
        upperboundDateSpinnerForSearchPlace.setModel(model2);
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(upperboundDateSpinnerForSearchPlace, "MM/dd/yyyy");
        DateFormatter formatter2 = (DateFormatter)editor2.getTextField().getFormatter();
        formatter2.setAllowsInvalid(false);
        formatter2.setOverwriteMode(true);
        upperboundDateSpinnerForSearchPlace.setEditor(editor2);

        SpinnerDateModel model3 = new SpinnerDateModel();
        SpinnerDateModel model4 = new SpinnerDateModel();
        SpinnerDateModel model5 = new SpinnerDateModel();
        startingAtField_Person.setModel(model3);
        endingAtField_Person.setModel(model4);
        startedAfterField_Virus.setModel(model5);
        JSpinner.DateEditor editor3 = new JSpinner.DateEditor(startingAtField_Person, "MM/dd/yyyy");
        JSpinner.DateEditor editor4 = new JSpinner.DateEditor(endingAtField_Person, "MM/dd/yyyy");
        JSpinner.DateEditor editor5 = new JSpinner.DateEditor(startedAfterField_Virus, "MM/dd/yyyy");
        DateFormatter formatter3 = (DateFormatter)editor2.getTextField().getFormatter();
        DateFormatter formatter4 = (DateFormatter)editor2.getTextField().getFormatter();
        DateFormatter formatter5 = (DateFormatter)editor2.getTextField().getFormatter();
        formatter3.setAllowsInvalid(false);
        formatter3.setOverwriteMode(true);
        formatter4.setAllowsInvalid(false);
        formatter4.setOverwriteMode(true);
        formatter5.setAllowsInvalid(false);
        formatter5.setOverwriteMode(true);
        startingAtField_Person.setEditor(editor3);
        endingAtField_Person.setEditor(editor4);
        startedAfterField_Virus.setEditor(editor5);

        //set up this Database Dialog
        setTitle("Virus Database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setVisible(true);

        searchCureButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nationality = joinNationalityField.getText();
                int sinum = Integer.parseInt(joinSinumField.getText());
                CnameMedicineID[] result = virusDatabase.searchCountryHasCure(nationality, sinum);
                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                } else {
                    JOptionPane.showMessageDialog(null,"No cure in the patient's country of residence");
                }
            }
        }));

        showRouteIDCountButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RouteIDCount[] result = virusDatabase.getRouteIDCount();

                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                }
            }
        }));

        showNationalityCountButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NationalityCount[] result = virusDatabase.getNationalityCount();

                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                }
            }
        }));

        showRouteButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Route[] result = virusDatabase.showRoute();

                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                }
            }
        }));

        deleteRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int routeID = Integer.parseInt(deleteRouteTextField.getText());
                    virusDatabase.deleteRoute(routeID);
                    showRouteButton.doClick();
                } catch(Exception exception) {
                    JOptionPane.showMessageDialog(null,"Please write a valid integer");
                }
            }
        });

        submitPlaceData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = nameTextFieldFromPlace.getText();
                String houseNumString = houseNumTextFieldFromPlace.getText();
                String streetName = streetNameTextFieldFromPlace.getText();
                String postalCode = postalCodeTextFieldFromPlace.getText();
                String cname = cnameTextFieldFromPlace.getText();

                //check if the user put any input
                if (name.isEmpty() || houseNumString.isEmpty() || streetName.isEmpty() || postalCode.isEmpty() || cname.isEmpty()) {

                    JOptionPane.showMessageDialog(null,"Please put all the data for Place.");
                }
                else {

                    int houseNum = Integer.parseInt(houseNumString);
                    Place place = new Place(name, houseNum, streetName, postalCode, cname);
                    virusDatabase.insertPlace(place);

                    // Check if the data is successfully added by showing the place table
                    showPlaceTable.doClick();
                }
            }
        });

        showPlaceTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Place[] result = virusDatabase.getPlaceInfo();

                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                }
            }
        });

        showCountryTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Country[] result = virusDatabase.getCountryInfo();

                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                }
            }
        });
        searchPlaceByDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Date lowerBoundDate = (Date)lowerboundDateSpinnerForSearchPlace.getModel().getValue();
                Date upperBoundDate = (Date)upperboundDateSpinnerForSearchPlace.getModel().getValue();

                System.out.println(upperBoundDate.toString());

                Place[] result = virusDatabase.getPlacesInfectedVisited(lowerBoundDate, upperBoundDate);

                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                }
            }
        });

        showPersonTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Person[] result = virusDatabase.getPersonInfo();
                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                }
            }
        });

        searchPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nationality = nationality_Person.getText();
                int routeNum = Integer.parseInt(visitedRouteField_Person.getText());
                Date startingAt = (Date)startingAtField_Person.getModel().getValue();
                Date endingAt = (Date)endingAtField_Person.getModel().getValue();

                Person[] result = virusDatabase.searchPersonInfo(nationality, routeNum, startingAt, endingAt);
                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                } else {
                    JOptionPane.showMessageDialog(null,"No person with those traits in the database is available.");
                }
            }
        });

        updateRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nationality = nationality_Person.getText();
                int sinum = Integer.parseInt(sinumField_Person.getText());
                int routeNum = Integer.parseInt(visitedRouteField_Person.getText());
                Date startingAt = (Date)startingAtField_Person.getModel().getValue();
                Date endingAt = (Date)endingAtField_Person.getModel().getValue();

                virusDatabase.updateRoute(nationality, sinum, routeNum, startingAt, endingAt);

                // Check if the data is successfully added by showing the place table
                showRoutesButton.doClick();

            }
        });

        searchVirus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date startedAfter = (Date)startedAfterField_Virus.getModel().getValue();

                MedicineKills[] result = virusDatabase.searchVirus(startedAfter);
                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                } else {
                    JOptionPane.showMessageDialog(null,"Such cures do not exist.");
                }

            }
        });
        
        SearchNotinfectedPeopleWhoWentToDangerousRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Person[] result = virusDatabase.searchNotInfectedButMightInfected();
                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                }
            }
        });

        showRoutesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoutePerson_WentAt[] result = virusDatabase.getRoutePeopleInfo();
                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                }
            }
        });

        showCuresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kills[] result = virusDatabase.getCuresKillingVirus();
                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                } else {
                    JOptionPane.showMessageDialog(null,"No cures can be found.");
                }
            }
        });
        showNotInfectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person[] result = virusDatabase.getNotInfectedInfo();
                if (result.length != 0) {
                    String[][] data = new String[result.length][];
                    for (int i = 0; i < result.length; i++) {
                        data[i] = result[i].tupleToListOfString();
                    }
                    String[] columnNames = result[0].columnNameListOfString();

                    createSearchOutputDialog(data, columnNames);
                }
            }
        });
    }

    public void createSearchOutputDialog(String[][] data, String[] columnNames) {

        searchOutput = new SearchOutput(virusDatabase, data, columnNames);
        searchOutput.add(searchOutput.searchOutput);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
