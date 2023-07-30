package com.project.Crud.controller;

import com.project.Crud.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class UserController {
    ModelAndView modelAndView = new ModelAndView();
    /**
     * Structure to hold the user object
     */
    private HashMap<Integer, User> users = new HashMap<>();

    /**
     * Location of the XML file to store the user objects
     */
    File file = new File("C:\\IntelliJ Projects\\Crud\\src\\main\\java\\com\\project\\Crud\\controller\\UserData.xml");
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.parse(file); Element root;

    boolean applicationStart = true;

    public UserController() throws ParserConfigurationException, IOException, SAXException {
    }

    /**
     * Displays the home page
     */
    @RequestMapping(value="/", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView home() throws ParserConfigurationException, IOException, SAXException {
        // Loads the XML file into the HashMap
        // Method should only run once when the application starts
        if(applicationStart) {
            loadXML();
            applicationStart = false;
        }

        // Adds the users HashMap to the model for display
        modelAndView.addObject("usersList", new ArrayList<>(users.values()));
        modelAndView.setViewName("home");
        return modelAndView;
    }

    /**
     * Displays the add user page
     */
    @RequestMapping(value="/addUser-page", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addUserPage(User user, Model model) {
        listsOptions(model);
        // Adds the user object to the model for user creation
        modelAndView.addObject("user", user);
        modelAndView.addObject(model);
        modelAndView.setViewName("addUser");
        return modelAndView;
    }

    /**
     * Adds the user object to the users HashMap
     * and to the XML file
     */
    @RequestMapping(value="/addUser", method={RequestMethod.GET, RequestMethod.POST})
    public String addUser(@ModelAttribute("user") @Validated User user, BindingResult result) throws IOException, SAXException, TransformerException {
        // Checks for errors in the user object
        if(result.hasErrors())
            return "redirect:/addUser-page";

        // Adds the user object to the users HashMap
        users.put(user.getId(), user);

        // Adds the user object to the XML file
        addUserToXML(user);

        return "redirect:/";
    }

    /**
     * Displays the edit user page
     */
    @RequestMapping(value="/{id}/editUser-page", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView editUserPage(@PathVariable("id") Integer id, Model model) {
        listsOptions(model);

        // Adds the user object with the matching id to the model for user editing
        modelAndView.addObject("user", users.get(id));
        modelAndView.addObject(model);
        modelAndView.setViewName("editUser");
        return modelAndView;
    }

    /**
     * Edits the user object in the users HashMap
     * and in the XML file
     */
    @RequestMapping(value="/editUser", method={RequestMethod.GET, RequestMethod.POST})
    public String editUser(@ModelAttribute("user") @Validated User user, BindingResult result) throws IOException, SAXException, TransformerException {
        // Checks for errors in the user object
        if(result.hasErrors()) {
            String url = "/" + user.getId() + "/editUser-page";
            return "redirect:" + url;
        }

        // Edits the user object in the users HashMap
        users.replace(user.getId(), user);

        // Edits the user object in the XML file
        editUserInXML(user);

        return "redirect:/";
    }

    /**
     * View the user object
     */
    @RequestMapping(value="/{id}/viewUser-page", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewUserPage(@PathVariable("id") Integer id) {
        // Adds the user object with the matching id to the model for user viewing
        modelAndView.addObject("user", users.get(id));
        modelAndView.setViewName("viewUser");
        return modelAndView;
    }

    /**
     * Deletes the user object from the users HashMap
     * and from the XML file
     */
    @RequestMapping(value="/{id}/deleteUser", method={RequestMethod.GET, RequestMethod.POST})
    public String deleteUser(@PathVariable("id") Integer id) throws IOException, SAXException, TransformerException {
        // Deletes the user object from the users HashMap
        users.remove(id);

        // Deletes the user object from the XML file
        deleteUserFromXML(users.get(id));

        return "redirect:/";
    }

    /**
     * Provides the options for the dropdown menus
     * and radiobuttons on the add user page
     */
    private void listsOptions(Model model) {
        List<String> languagesList = new ArrayList<>();
        languagesList.add("Python");
        languagesList.add("Java");
        languagesList.add("C++");
        languagesList.add("C#");
        languagesList.add("JavaScript");
        languagesList.add("PHP");
        languagesList.add("Ruby");
        languagesList.add("Go");
        model.addAttribute("languagesList", languagesList);

        List<String> frameworksList = new ArrayList<>();
        frameworksList.add("Django");
        frameworksList.add("Spring");
        frameworksList.add("React");
        frameworksList.add("Angular");
        frameworksList.add("Vue");
        frameworksList.add("Laravel");
        frameworksList.add("Ruby on Rails");
        frameworksList.add("Flask");
        model.addAttribute("frameworksList", frameworksList);

        List<String> teamList = new ArrayList<>();
        teamList.add("Front-end");
        teamList.add("Back-end");
        teamList.add("Full-stack");
        model.addAttribute("teamList", teamList);

        List<String> payList = new ArrayList<>();
        payList.add("Hourly");
        payList.add("Salary");
        model.addAttribute("payList", payList);

        List<String> yearsOfExperienceList = new ArrayList<>();
        yearsOfExperienceList.add("0-1");
        yearsOfExperienceList.add("1-2");
        yearsOfExperienceList.add("2-3");
        yearsOfExperienceList.add("3-4");
        yearsOfExperienceList.add("4-5");
        yearsOfExperienceList.add("5+");
        model.addAttribute("yearsOfExperienceList", yearsOfExperienceList);
    }

    /**
     * When called will the add the new user to the XML file
     */
    private void addUserToXML(User user) throws TransformerException {
        // Gets the root element of the XML file
        // Will create one if it doesn't exist
        if(doc.getDocumentElement() == null) {
            root = doc.createElement("Users");
            doc.appendChild(root);
        } else {
            root = doc.getDocumentElement();
        }

        // Creates the user element
        Element userElement = doc.createElement("User");
        root.appendChild(userElement);

        // Creating each element of the user object
        Element id = doc.createElement("id");
        id.appendChild(doc.createTextNode(user.getId().toString()));
        userElement.appendChild(id);

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(user.getName()));
        userElement.appendChild(name);

        Element email = doc.createElement("email");
        email.appendChild(doc.createTextNode(user.getEmail()));
        userElement.appendChild(email);

        Element bio = doc.createElement("bio");
        bio.appendChild(doc.createTextNode(user.getBio()));
        userElement.appendChild(bio);

        Element experience = doc.createElement("experience");
        experience.appendChild(doc.createTextNode(user.getYearsOfExperience()));
        userElement.appendChild(experience);

        Element languages = doc.createElement("programmingLanguages");
        for(String language : user.getProgrammingLanguages()) {
            Element languageElement = doc.createElement("language");
            languageElement.appendChild(doc.createTextNode(language));
            languages.appendChild(languageElement);
        }
        userElement.appendChild(languages);

        Element frameworks = doc.createElement("frameworks");
        for(String framework : user.getFrameworks()) {
            Element frameworkElement = doc.createElement("framework");
            frameworkElement.appendChild(doc.createTextNode(framework));
            frameworks.appendChild(frameworkElement);
        }
        userElement.appendChild(frameworks);

        Element team = doc.createElement("team");
        team.appendChild(doc.createTextNode(user.getTeam().toString()));
        userElement.appendChild(team);

        Element pay = doc.createElement("pay");
        if (user.getPay() != null) {
            pay.appendChild(doc.createTextNode(user.getPay().toString()));
        } else {
            pay.appendChild(doc.createTextNode("Unpaid"));
        }
        userElement.appendChild(pay);

        writeToXML(); // Writes the changes to the XML file
    }

    /**
     * When called will edit the respective user in the XML file
     */
    private void editUserInXML(User user) throws TransformerException {
        // Gets the users from the XML file and puts them into a NodeList
        NodeList userList = doc.getElementsByTagName("User");

        for (int i = 0; i < userList.getLength(); i++) {
            Element userXML = (Element) userList.item(i);

            if (userXML.getElementsByTagName("id").item(0).getTextContent().equals(user.getId().toString())) {
                userXML.getElementsByTagName("name").item(0).setTextContent(user.getName());
                userXML.getElementsByTagName("email").item(0).setTextContent(user.getEmail());
                userXML.getElementsByTagName("bio").item(0).setTextContent(user.getBio());
                userXML.getElementsByTagName("experience").item(0).setTextContent(user.getYearsOfExperience());
                userXML.getElementsByTagName("team").item(0).setTextContent(user.getTeam().toString());
                userXML.getElementsByTagName("pay").item(0).setTextContent(user.getPay().toString());

                Element languages = (Element) userXML.getElementsByTagName("programmingLanguages").item(0);
                NodeList languageList = languages.getElementsByTagName("language");
                for (int j = 0; j < languageList.getLength(); j++) {
                    languages.removeChild(languageList.item(j));
                }
                for (String language : user.getProgrammingLanguages()) {
                    Element languageElement = doc.createElement("language");
                    languageElement.appendChild(doc.createTextNode(language));
                    languages.appendChild(languageElement);
                }

                Element frameworks = (Element) userXML.getElementsByTagName("frameworks").item(0);
                NodeList frameworkList = frameworks.getElementsByTagName("framework");
                for (int j = 0; j < frameworkList.getLength(); j++) {
                    frameworks.removeChild(frameworkList.item(j));
                }
                for (String framework : user.getFrameworks()) {
                    Element frameworkElement = doc.createElement("framework");
                    frameworkElement.appendChild(doc.createTextNode(framework));
                    frameworks.appendChild(frameworkElement);
                }

                break;
            }
        }
        writeToXML();
    }

    /**
     * When called will delete the respective user in the XML file
     */
    private void deleteUserFromXML(User user) throws TransformerException {
        // Gets the users from the XML file and puts them into a NodeList
        NodeList userList = doc.getElementsByTagName("User");

        for (int i = 0; i < userList.getLength(); i++) {
            Element userXML = (Element) userList.item(i);
            if (userXML.getElementsByTagName("id").item(0).getTextContent().equals(user.getId().toString())) {
                root.removeChild(userXML);
                break;
            }
        }
        writeToXML();
    }

    /**
     * Opens the XML file and loads the users from it into the users HashMap
     */
    private void loadXML() throws ParserConfigurationException, SAXException, IOException {
        // Gets the users from the XML file and puts them into a NodeList
        NodeList userList = doc.getElementsByTagName("User");

        // Loops through the NodeList creates and adds each user to the users HashMap
        for(int i = 0; i < userList.getLength(); i++) {
            Element userElement = (Element) userList.item(i);
            User user = new User();

            user.setId(Integer.parseInt(userElement.getElementsByTagName("id").item(0).getTextContent()));
            user.setName(userElement.getElementsByTagName("name").item(0).getTextContent());
            user.setEmail(userElement.getElementsByTagName("email").item(0).getTextContent());
            user.setBio(userElement.getElementsByTagName("bio").item(0).getTextContent());
            user.setYearsOfExperience(userElement.getElementsByTagName("experience").item(0).getTextContent());
            user.setProgrammingLanguages(Collections.singletonList(userElement.getElementsByTagName("language").item(0).getTextContent()));
            user.setFrameworks(Collections.singletonList(userElement.getElementsByTagName("framework").item(0).getTextContent()));
            user.setTeam(Collections.singletonList(userElement.getElementsByTagName("team").item(0).getTextContent()));
            user.setPay(Collections.singletonList(userElement.getElementsByTagName("pay").item(0).getTextContent()));

            users.put(user.getId(), user);
        }
    }

    /**
     * Writes to the XML file
     */
    private void writeToXML() throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance(); // Creates a new TransformerFactory
        Transformer transformer = tf.newTransformer(); // Allows for the XML file to be written to
        DOMSource domSource = new DOMSource(doc); // Gets the root Node from the XML file
        StreamResult sr = new StreamResult(new File("users.xml")); // Gets the XML file to write to

        // Formats the XML file
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        transformer.transform(domSource, sr); // Writes the changes to the XML file
    }

    /*                        SIDE MISSIONS                           */
    /* -------------------------------------------------------------- */

    /**
     * Shows a statistical page of programming languages and frameworks the users have
     */
    @RequestMapping(value = "/stats", method = {RequestMethod.GET, RequestMethod.POST})
    private ModelAndView statistics() {
        if(users.isEmpty()) {
            return new ModelAndView("redirect:/");
        }

        HashMap<String, Integer> langFreq = new HashMap<>(); // Acquires the languages and their frequency
        HashMap<String, Integer> frameFreq = new HashMap<>(); // Acquires the frameworks and their frequency
        float langPercentage, framePercentage; // The percentage of each language and framework
        String lang, frame;

        for(User person : users.values()){
            for(String language : person.getProgrammingLanguages()){
                // If the language is already in the HashMap, increment the frequency by 1
                langFreq.put(language, langFreq.getOrDefault(language, 0) + 1);
            }
            for(String framework : person.getFrameworks()){
                frameFreq.put(framework, frameFreq.getOrDefault(framework, 0) + 1);
            }
        }

        // Get the most frequent language and framework
        lang = Collections.max(langFreq.entrySet(), Map.Entry.comparingByValue()).getKey();
        frame = Collections.max(frameFreq.entrySet(), Map.Entry.comparingByValue()).getKey();

        // Calculates the percentage
        langPercentage = Math.round(((float)langFreq.get(lang) / users.size()) * 100);
        framePercentage = Math.round(((float)frameFreq.get(frame) / users.size()) * 100);

        modelAndView.addObject("language", lang);
        modelAndView.addObject("framework", frame);
        modelAndView.addObject("langPercentage", langPercentage);
        modelAndView.addObject("framePercentage", framePercentage);
        modelAndView.setViewName("statistics");
        return modelAndView;
    }

    /**
     * A page that promotes the user
     */
    @RequestMapping(value = "/{id}/promote", method = {RequestMethod.GET, RequestMethod.POST})
    private ModelAndView promote(@PathVariable("id") Integer id, Model model) throws TransformerException {
        listsOptions(model);
        modelAndView.addObject("user", users.get(id));
        modelAndView.setViewName("promote");
        return modelAndView;
    }
}
