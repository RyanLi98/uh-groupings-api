package edu.hawaii.its.api.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import edu.hawaii.its.api.type.*;
import edu.hawaii.its.api.configuration.SpringBootWebApplication;

import edu.internet2.middleware.grouperClient.ws.beans.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.when;

@ActiveProfiles("localTest")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootWebApplication.class})
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GroupingsServiceMockTest {
    @Value("${groupings.api.settings}")
    private String SETTINGS;

    @Value("${groupings.api.grouping_admins}")
    private String GROUPING_ADMINS;

    @Value("${groupings.api.grouping_apps}")
    private String GROUPING_APPS;

    @Value("${groupings.api.grouping_owners}")
    private String GROUPING_OWNERS;

    @Value("${groupings.api.grouping_superusers}")
    private String GROUPING_SUPERUSERS;

    @Value("${groupings.api.attributes}")
    private String ATTRIBUTES;

    @Value("${groupings.api.for_groups}")
    private String FOR_GROUPS;

    @Value("${groupings.api.for_memberships}")
    private String FOR_MEMBERSHIPS;

    @Value("${groupings.api.last_modified}")
    private String LAST_MODIFIED;

    @Value("${groupings.api.yyyymmddThhmm}")
    private String YYYYMMDDTHHMM;

    @Value("${groupings.api.uhgrouping}")
    private String UHGROUPING;

    @Value("${groupings.api.destinations}")
    private String DESTINATIONS;

    @Value("${groupings.api.listserv}")
    private String LISTSERV;

    @Value("${groupings.api.trio}")
    private String TRIO;

    @Value("${groupings.api.self_opted}")
    private String SELF_OPTED;

    @Value("${groupings.api.anyone_can}")
    private String ANYONE_CAN;

    @Value("${groupings.api.opt_in}")
    private String OPT_IN;

    @Value("${groupings.api.opt_out}")
    private String OPT_OUT;

    @Value("${groupings.api.basis}")
    private String BASIS;

    @Value("${groupings.api.basis_plus_include}")
    private String BASIS_PLUS_INCLUDE;

    @Value("${groupings.api.exclude}")
    private String EXCLUDE;

    @Value("${groupings.api.include}")
    private String INCLUDE;

    @Value("${groupings.api.owners}")
    private String OWNERS;

    @Value("${groupings.api.assign_type_group}")
    private String ASSIGN_TYPE_GROUP;

    @Value("${groupings.api.assign_type_immediate_membership}")
    private String ASSIGN_TYPE_IMMEDIATE_MEMBERSHIP;

    @Value("${groupings.api.subject_attribute_name_uuid}")
    private String SUBJECT_ATTRIBUTE_NAME_UID;

    @Value("${groupings.api.operation_assign_attribute}")
    private String OPERATION_ASSIGN_ATTRIBUTE;

    @Value("${groupings.api.operation_remove_attribute}")
    private String OPERATION_REMOVE_ATTRIBUTE;

    @Value("${groupings.api.operation_replace_values}")
    private String OPERATION_REPLACE_VALUES;

    @Value("${groupings.api.privilege_opt_out}")
    private String PRIVILEGE_OPT_OUT;

    @Value("${groupings.api.privilege_opt_in}")
    private String PRIVILEGE_OPT_IN;

    @Value("${groupings.api.every_entity}")
    private String EVERY_ENTITY;

    @Value("${groupings.api.is_member}")
    private String IS_MEMBER;

    @Value("${groupings.api.success}")
    private String SUCCESS;

    @Value("${groupings.api.failure}")
    private String FAILURE;

    @Value("${groupings.api.success_allowed}")
    private String SUCCESS_ALLOWED;

    @Value("$groupings.api.stem}")
    private String STEM;

    @Value("${groupings.api.test.username}")
    private String USERNAME;

    @Value("${groupings.api.test.name}")
    private String NAME;

    @Value("${groupings.api.test.uuid}")
    private String UUID;

    private static final String PATH_ROOT = "path:to:grouping";

    private static final String GROUPING_0_PATH = PATH_ROOT + 0;
    private static final String GROUPING_1_PATH = PATH_ROOT + 1;
    private static final String GROUPING_2_PATH = PATH_ROOT + 2;
    private static final String GROUPING_3_PATH = PATH_ROOT + 3;
    private static final String GROUPING_4_PATH = PATH_ROOT + 4;

    private static final String GROUPING_0_INCLUDE_PATH = GROUPING_0_PATH + ":include";
    private static final String GROUPING_0_EXCLUDE_PATH = GROUPING_0_PATH + ":exclude";
    private static final String GROUPING_0_BASIS_PATH = GROUPING_0_PATH + ":basis";
    private static final String GROUPING_0_OWNERS_PATH = GROUPING_0_PATH + ":owners";

    private static final String GROUPING_1_INCLUDE_PATH = GROUPING_1_PATH + ":include";
    private static final String GROUPING_1_EXCLUDE_PATH = GROUPING_1_PATH + ":exclude";
    private static final String GROUPING_1_BASIS_PATH = GROUPING_1_PATH + ":basis";
    private static final String GROUPING_1_OWNERS_PATH = GROUPING_1_PATH + ":owners";

    private static final String GROUPING_2_INCLUDE_PATH = GROUPING_2_PATH + ":include";
    private static final String GROUPING_2_EXCLUDE_PATH = GROUPING_2_PATH + ":exclude";
    private static final String GROUPING_2_BASIS_PATH = GROUPING_2_PATH + ":basis";
    private static final String GROUPING_2_OWNERS_PATH = GROUPING_2_PATH + ":owners";

    private static final String GROUPING_3_INCLUDE_PATH = GROUPING_3_PATH + ":include";
    private static final String GROUPING_3_EXCLUDE_PATH = GROUPING_3_PATH + ":exclude";
    private static final String GROUPING_3_BASIS_PATH = GROUPING_3_PATH + ":basis";
    private static final String GROUPING_3_OWNERS_PATH = GROUPING_3_PATH + ":owners";

    private static final String GROUPING_4_INCLUDE_PATH = GROUPING_4_PATH + ":include";
    private static final String GROUPING_4_EXCLUDE_PATH = GROUPING_4_PATH + ":exclude";
    private static final String GROUPING_4_BASIS_PATH = GROUPING_4_PATH + ":basis";
    private static final String GROUPING_4_OWNERS_PATH = GROUPING_4_PATH + ":owners";

    private final WsSubjectLookup EVERY_ENTITY_LOOKUP = new WsSubjectLookup(null, null, EVERY_ENTITY);

    private static final String ADMIN_USER = "admin";
    private static final WsSubjectLookup ADMIN_LOOKUP = new WsSubjectLookup(null, null, ADMIN_USER);
    private static final Person ADMIN_PERSON = new Person(ADMIN_USER, ADMIN_USER, ADMIN_USER);
    private List<Person> admins = new ArrayList<>();
    private Group adminGroup;

    private static final String APP_USER = "app";
    private static final WsSubjectLookup APP_LOOKUP = new WsSubjectLookup(null, null, APP_USER);
    private static final Person APP_PERSON = new Person(APP_USER, APP_USER, APP_USER);
    private List<Person> apps = new ArrayList<>();
    private Group appGroup;

    private DatabaseSetup databaseSetup;

    private List<Person> users = new ArrayList<>();
    private List<WsSubjectLookup> lookups = new ArrayList<>();

    @Autowired
    private GroupingsService groupingsService;

    @Autowired
    private GroupingRepository groupingRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Before
    public void setup() throws Exception {

        databaseSetup = new DatabaseSetup(personRepository, groupRepository, groupingRepository, membershipRepository);

        admins.add(ADMIN_PERSON);
        adminGroup = new Group(GROUPING_ADMINS, admins);
        personRepository.save(ADMIN_PERSON);
        groupRepository.save(adminGroup);

        admins.add(APP_PERSON);
        appGroup = new Group(GROUPING_APPS, apps);
        personRepository.save(APP_PERSON);
        groupRepository.save(appGroup);

        for (int i = 0; i < 100; i++) {
            String name = NAME + i;
            String uuid = UUID + i;
            String username = USERNAME + i;

            Person person = new Person(name, uuid, username);
            users.add(person);

            WsSubjectLookup lookup = new WsSubjectLookup(null, null, username);
            lookups.add(lookup);
        }
    }

    @Test
    public void construction() {
        assertNotNull(groupingsService);
    }

    @Test
    public void databaseTest() {
        Iterable<Person> persons = personRepository.findAll();
        Iterable<Group> groups = groupRepository.findAll();
        Iterable<Grouping> groupings = groupingRepository.findAll();
        assertNotNull(persons);
        assertNotNull(groups);
        assertNotNull(groupings);
    }

    @Test
    public void assignOwnershipTest() {

        Person randomUser = personRepository.findByUsername(users.get(1).getUsername());
        Grouping grouping = groupingRepository.findByPath(GROUPING_0_PATH);

        assertFalse(grouping.getOwners().getMembers().contains(randomUser));

        GroupingsServiceResult randomUserAdds = groupingsService.assignOwnership(GROUPING_0_PATH, randomUser.getUsername(), randomUser.getUsername());
        grouping = groupingRepository.findByPath(GROUPING_0_PATH);
        assertFalse(grouping.getOwners().getMembers().contains(randomUser));
        assertNotEquals(randomUserAdds.getResultCode(), SUCCESS);

        GroupingsServiceResult ownerAdds = groupingsService.assignOwnership(GROUPING_0_PATH, users.get(0).getUsername(), randomUser.getUsername());
        grouping = groupingRepository.findByPath(GROUPING_0_PATH);
        assertTrue(grouping.getOwners().getMembers().contains(randomUser));
        assertEquals(ownerAdds.getResultCode(), SUCCESS);

        GroupingsServiceResult adminAdds = groupingsService.assignOwnership(GROUPING_0_PATH, ADMIN_USER, randomUser.getUsername());
        grouping = groupingRepository.findByPath(GROUPING_0_PATH);
        assertTrue(grouping.getOwners().getMembers().contains(randomUser));
        assertEquals(SUCCESS, adminAdds.getResultCode());
    }


    @Test
    public void changeListservStatusTest() {

        Grouping grouping = groupingRepository.findByPath(GROUPING_4_PATH);
        assertFalse(grouping.isListservOn());

        GroupingsServiceResult turnOnWhenOffRandom = groupingsService.changeListservStatus(GROUPING_4_PATH, users.get(1).getUsername(), true);
        grouping = groupingRepository.findByPath(GROUPING_4_PATH);
        assertFalse(grouping.isListservOn());

        GroupingsServiceResult turnOnWhenOffOwner = groupingsService.changeListservStatus(GROUPING_4_PATH, users.get(0).getUsername(), true);
        grouping = groupingRepository.findByPath(GROUPING_4_PATH);
        assertTrue(grouping.isListservOn());

        GroupingsServiceResult turnOnWhenOnRandom = groupingsService.changeListservStatus(GROUPING_4_PATH, users.get(1).getUsername(), true);
        grouping = groupingRepository.findByPath(GROUPING_4_PATH);
        assertTrue(grouping.isListservOn());

        GroupingsServiceResult turnOnWhenOnOwner = groupingsService.changeListservStatus(GROUPING_4_PATH, users.get(0).getUsername(), true);
        grouping = groupingRepository.findByPath(GROUPING_4_PATH);
        assertTrue(grouping.isListservOn());

        GroupingsServiceResult turnOnWhenOnAdmin = groupingsService.changeListservStatus(GROUPING_4_PATH, ADMIN_USER, true);
        grouping = groupingRepository.findByPath(GROUPING_4_PATH);
        assertTrue(grouping.isListservOn());

        GroupingsServiceResult turnOffWhenOnRandom = groupingsService.changeListservStatus(GROUPING_4_PATH, users.get(1).getUsername(), false);
        grouping = groupingRepository.findByPath(GROUPING_4_PATH);
        assertTrue(grouping.isListservOn());

        GroupingsServiceResult turnOffWhenOnOwner = groupingsService.changeListservStatus(GROUPING_4_PATH, users.get(0).getUsername(), false);
        grouping = groupingRepository.findByPath(GROUPING_4_PATH);
        assertFalse(grouping.isListservOn());

        GroupingsServiceResult turnOnWhenOffAdmin = groupingsService.changeListservStatus(GROUPING_4_PATH, ADMIN_USER, true);
        grouping = groupingRepository.findByPath(GROUPING_4_PATH);
        assertTrue(grouping.isListservOn());

        GroupingsServiceResult turnOffWhenOnAdmin = groupingsService.changeListservStatus(GROUPING_4_PATH, ADMIN_USER, false);
        grouping = groupingRepository.findByPath(GROUPING_4_PATH);
        assertFalse(grouping.isListservOn());

        GroupingsServiceResult turnOffWhenOffRandom = groupingsService.changeListservStatus(GROUPING_4_PATH, users.get(1).getUsername(), false);
        grouping = groupingRepository.findByPath(GROUPING_4_PATH);
        assertFalse(grouping.isListservOn());

        GroupingsServiceResult turnOffWhenOffOwner = groupingsService.changeListservStatus(GROUPING_4_PATH, users.get(0).getUsername(), false);
        grouping = groupingRepository.findByPath(GROUPING_4_PATH);
        assertFalse(grouping.isListservOn());

        GroupingsServiceResult turnOffWhenOffAdmin = groupingsService.changeListservStatus(GROUPING_4_PATH, ADMIN_USER, false);
        grouping = groupingRepository.findByPath(GROUPING_4_PATH);
        assertFalse(grouping.isListservOn());

        assertTrue(turnOnWhenOnRandom.getResultCode().startsWith(FAILURE));
        assertTrue(turnOnWhenOnOwner.getResultCode().startsWith(SUCCESS));
        assertTrue(turnOnWhenOnAdmin.getResultCode().startsWith(SUCCESS));

        assertTrue(turnOffWhenOnRandom.getResultCode().startsWith(FAILURE));
        assertTrue(turnOffWhenOnOwner.getResultCode().startsWith(SUCCESS));
        assertTrue(turnOffWhenOnAdmin.getResultCode().startsWith(SUCCESS));

        assertTrue(turnOnWhenOffRandom.getResultCode().startsWith(FAILURE));
        assertTrue(turnOnWhenOffOwner.getResultCode().startsWith(SUCCESS));
        assertTrue(turnOnWhenOffAdmin.getResultCode().startsWith(SUCCESS));

        assertTrue(turnOffWhenOffRandom.getResultCode().startsWith(FAILURE));
        assertTrue(turnOffWhenOffOwner.getResultCode().startsWith(SUCCESS));
        assertTrue(turnOffWhenOffAdmin.getResultCode().startsWith(SUCCESS));
    }


    @Test
    public void changeOptInStatusTest() {

        List<GroupingsServiceResult> turnOnWhenOnRandom = groupingsService.changeOptInStatus(GROUPING_0_PATH, users.get(1).getUsername(), true);
        List<GroupingsServiceResult> turnOnWhenOnOwner = groupingsService.changeOptInStatus(GROUPING_0_PATH, users.get(0).getUsername(), true);
        List<GroupingsServiceResult> turnOnWhenOnAdmin = groupingsService.changeOptInStatus(GROUPING_0_PATH, ADMIN_USER, true);

        List<GroupingsServiceResult> turnOffWhenOnRandom = groupingsService.changeOptInStatus(GROUPING_0_PATH, users.get(1).getUsername(), false);
        List<GroupingsServiceResult> turnOffWhenOnOwner = groupingsService.changeOptInStatus(GROUPING_0_PATH, users.get(0).getUsername(), false);

        List<GroupingsServiceResult> turnOffWhenOffRandom = groupingsService.changeOptInStatus(GROUPING_0_PATH, users.get(1).getUsername(), false);
        List<GroupingsServiceResult> turnOffWhenOffOwner = groupingsService.changeOptInStatus(GROUPING_0_PATH, users.get(0).getUsername(), false);
        List<GroupingsServiceResult> turnOffWhenOffAdmin = groupingsService.changeOptInStatus(GROUPING_0_PATH, ADMIN_USER, false);

        List<GroupingsServiceResult> turnOnWhenOffRandom = groupingsService.changeOptInStatus(GROUPING_0_PATH, users.get(1).getUsername(), true);
        List<GroupingsServiceResult> turnOnWhenOffOwner = groupingsService.changeOptInStatus(GROUPING_0_PATH, users.get(0).getUsername(), true);

        List<GroupingsServiceResult> turnOffWhenOnAdmin = groupingsService.changeOptInStatus(GROUPING_0_PATH, ADMIN_USER, false);

        List<GroupingsServiceResult> turnOnWhenOffAdmin = groupingsService.changeOptInStatus(GROUPING_0_PATH, ADMIN_USER, true);


        assertTrue(turnOnWhenOnRandom.get(0).getResultCode().startsWith(FAILURE));
        assertTrue(turnOnWhenOnOwner.get(0).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOnWhenOnOwner.get(1).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOnWhenOnOwner.get(2).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOnWhenOnAdmin.get(0).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOnWhenOnAdmin.get(1).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOnWhenOnAdmin.get(2).getResultCode().startsWith(SUCCESS));

        assertTrue(turnOffWhenOnRandom.get(0).getResultCode().startsWith(FAILURE));
        assertEquals(SUCCESS, turnOffWhenOnOwner.get(0).getResultCode());
        assertEquals(SUCCESS, turnOffWhenOnOwner.get(1).getResultCode());
        assertEquals(SUCCESS, turnOffWhenOnOwner.get(2).getResultCode());
        assertEquals(SUCCESS, turnOffWhenOnAdmin.get(0).getResultCode());
        assertEquals(SUCCESS, turnOffWhenOnAdmin.get(1).getResultCode());
        assertEquals(SUCCESS, turnOffWhenOnAdmin.get(2).getResultCode());

        assertTrue(turnOnWhenOffRandom.get(0).getResultCode().startsWith(FAILURE));
        assertEquals(SUCCESS, turnOnWhenOffOwner.get(0).getResultCode());
        assertEquals(SUCCESS, turnOnWhenOffOwner.get(1).getResultCode());
        assertEquals(SUCCESS, turnOnWhenOffOwner.get(2).getResultCode());
        assertEquals(SUCCESS, turnOnWhenOffAdmin.get(0).getResultCode());
        assertEquals(SUCCESS, turnOnWhenOffAdmin.get(1).getResultCode());
        assertEquals(SUCCESS, turnOnWhenOffAdmin.get(2).getResultCode());

        assertTrue(turnOffWhenOffRandom.get(0).getResultCode().startsWith(FAILURE));
        assertTrue(turnOffWhenOffOwner.get(0).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOffWhenOffOwner.get(1).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOffWhenOffOwner.get(2).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOffWhenOffAdmin.get(0).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOffWhenOffAdmin.get(1).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOffWhenOffAdmin.get(2).getResultCode().startsWith(SUCCESS));
    }

    @Test
    public void changeOptOutStatusTest() {

        List<GroupingsServiceResult> turnOnWhenOnRandom = groupingsService.changeOptOutStatus(GROUPING_1_PATH, users.get(1).getUsername(), true);
        List<GroupingsServiceResult> turnOnWhenOnOwner = groupingsService.changeOptOutStatus(GROUPING_1_PATH, users.get(0).getUsername(), true);
        List<GroupingsServiceResult> turnOnWhenOnAdmin = groupingsService.changeOptOutStatus(GROUPING_1_PATH, ADMIN_USER, true);

        List<GroupingsServiceResult> turnOffWhenOnRandom = groupingsService.changeOptOutStatus(GROUPING_1_PATH, users.get(1).getUsername(), false);
        List<GroupingsServiceResult> turnOffWhenOnOwner = groupingsService.changeOptOutStatus(GROUPING_1_PATH, users.get(0).getUsername(), false);

        List<GroupingsServiceResult> turnOnWhenOffRandom = groupingsService.changeOptOutStatus(GROUPING_1_PATH, users.get(1).getUsername(), true);
        List<GroupingsServiceResult> turnOnWhenOffOwner = groupingsService.changeOptOutStatus(GROUPING_1_PATH, users.get(0).getUsername(), true);

        List<GroupingsServiceResult> turnOffWhenOnAdmin = groupingsService.changeOptOutStatus(GROUPING_1_PATH, ADMIN_USER, false);

        List<GroupingsServiceResult> turnOffWhenOffRandom = groupingsService.changeOptOutStatus(GROUPING_1_PATH, users.get(1).getUsername(), false);
        List<GroupingsServiceResult> turnOffWhenOffOwner = groupingsService.changeOptOutStatus(GROUPING_1_PATH, users.get(0).getUsername(), false);
        List<GroupingsServiceResult> turnOffWhenOffAdmin = groupingsService.changeOptOutStatus(GROUPING_1_PATH, ADMIN_USER, false);

        List<GroupingsServiceResult> turnOnWhenOffAdmin = groupingsService.changeOptOutStatus(GROUPING_1_PATH, ADMIN_USER, true);

        assertTrue(turnOnWhenOnRandom.get(0).getResultCode().startsWith(FAILURE));
        assertTrue(turnOnWhenOnOwner.get(0).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOnWhenOnOwner.get(1).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOnWhenOnOwner.get(2).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOnWhenOnAdmin.get(0).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOnWhenOnAdmin.get(1).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOnWhenOnAdmin.get(2).getResultCode().startsWith(SUCCESS));

        assertTrue(turnOffWhenOnRandom.get(0).getResultCode().startsWith(FAILURE));
        assertEquals(SUCCESS, turnOffWhenOnOwner.get(0).getResultCode());
        assertEquals(SUCCESS, turnOffWhenOnOwner.get(1).getResultCode());
        assertEquals(SUCCESS, turnOffWhenOnOwner.get(2).getResultCode());
        assertEquals(SUCCESS, turnOffWhenOnAdmin.get(0).getResultCode());
        assertEquals(SUCCESS, turnOffWhenOnAdmin.get(1).getResultCode());
        assertEquals(SUCCESS, turnOffWhenOnAdmin.get(2).getResultCode());

        assertTrue(turnOnWhenOffRandom.get(0).getResultCode().startsWith(FAILURE));
        assertEquals(SUCCESS, turnOnWhenOffOwner.get(0).getResultCode());
        assertEquals(SUCCESS, turnOnWhenOffOwner.get(1).getResultCode());
        assertEquals(SUCCESS, turnOnWhenOffOwner.get(2).getResultCode());
        assertEquals(SUCCESS, turnOnWhenOffAdmin.get(0).getResultCode());
        assertEquals(SUCCESS, turnOnWhenOffAdmin.get(1).getResultCode());
        assertEquals(SUCCESS, turnOnWhenOffAdmin.get(2).getResultCode());

        assertTrue(turnOffWhenOffRandom.get(0).getResultCode().startsWith(FAILURE));
        assertTrue(turnOffWhenOffOwner.get(0).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOffWhenOffOwner.get(1).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOffWhenOffOwner.get(2).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOffWhenOffAdmin.get(0).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOffWhenOffAdmin.get(1).getResultCode().startsWith(SUCCESS));
        assertTrue(turnOffWhenOffAdmin.get(2).getResultCode().startsWith(SUCCESS));
    }

    @Test
    public void removeOwnershipTest() {

        //non-owner/non-admin tries to remove ownership
        GroupingsServiceResult randomUserRemoves = groupingsService.removeOwnership(GROUPING_0_PATH, users.get(1).getUsername(), users.get(1).getUsername());
        assertTrue(randomUserRemoves.getResultCode().startsWith(FAILURE));

        //add owner for owner to remove
        groupingsService.addMemberAs(users.get(0).getUsername(), GROUPING_0_OWNERS_PATH, users.get(1).getUsername());

        //owner tries to remove other ownership
        GroupingsServiceResult ownerRemoves = groupingsService.removeOwnership(GROUPING_0_PATH, users.get(0).getUsername(), users.get(1).getUsername());
        assertEquals(SUCCESS, ownerRemoves.getResultCode());

        //try to remove ownership from user that is not an owner
        GroupingsServiceResult ownerRemovesNonOwner = groupingsService.removeOwnership(GROUPING_0_PATH, users.get(0).getUsername(), users.get(1).getUsername());
        assertEquals(SUCCESS, ownerRemovesNonOwner.getResultCode());

        //add owner for admin to remove
        groupingsService.addMemberAs(users.get(0).getUsername(), GROUPING_0_OWNERS_PATH, users.get(1).getUsername());

        //admin tries to remove ownership
        GroupingsServiceResult adminRemoves = groupingsService.removeOwnership(GROUPING_0_PATH, ADMIN_USER, users.get(1).getUsername());
        assertEquals(adminRemoves.getResultCode(), SUCCESS);
    }

    @Test
    public void getGroupingTest() {
        Grouping grouping = groupingRepository.findByPath(GROUPING_0_PATH);

        Grouping groupingRandom = groupingsService.getGrouping(GROUPING_0_PATH, users.get(1).getUsername());
        Grouping groupingOwner = groupingsService.getGrouping(GROUPING_0_PATH, users.get(0).getUsername());
        Grouping groupingAdmin = groupingsService.getGrouping(GROUPING_0_PATH, ADMIN_USER);

        assertEquals(0, groupingRandom.getComposite().getMembers().size());
        assertEquals(0, groupingRandom.getInclude().getMembers().size());
        assertEquals(0, groupingRandom.getExclude().getMembers().size());
        assertEquals(0, groupingRandom.getBasis().getMembers().size());
        assertEquals(0, groupingRandom.getOwners().getMembers().size());

        assertTrue(groupingOwner.getComposite().getNames().contains(users.get(0).getName()));
        assertTrue(groupingOwner.getComposite().getUsernames().contains(users.get(0).getUsername()));
        assertTrue(groupingOwner.getComposite().getUuids().contains(users.get(0).getUuid()));
        assertTrue(groupingOwner.getInclude().getNames().contains(users.get(5).getName()));
        assertTrue(groupingOwner.getExclude().getNames().contains(users.get(2).getName()));
        assertTrue(groupingOwner.getBasis().getNames().contains(users.get(4).getName()));
        assertTrue(groupingOwner.getOwners().getNames().contains(users.get(0).getName()));

        assertTrue(groupingAdmin.getComposite().getNames().contains(users.get(0).getName()));
        assertTrue(groupingAdmin.getComposite().getUsernames().contains(users.get(0).getUsername()));
        assertTrue(groupingAdmin.getComposite().getUuids().contains(users.get(0).getUuid()));
        assertTrue(groupingAdmin.getInclude().getNames().contains(users.get(5).getName()));
        assertTrue(groupingAdmin.getExclude().getNames().contains(users.get(2).getName()));
        assertTrue(groupingAdmin.getBasis().getNames().contains(users.get(4).getName()));
        assertTrue(groupingAdmin.getOwners().getNames().contains(users.get(0).getName()));
    }

    @Test
    public void getMyGroupingsTest() {

        //todo
    }

    @Test
    public void optInTest() {

        //opt in Permission for include group false
        List<GroupingsServiceResult> optInResults = groupingsService.optIn(users.get(1).getUsername(), GROUPING_0_PATH);

        assertTrue(optInResults.get(0).getResultCode().startsWith(FAILURE));

        //opt in Permission for include group true
        WsAttributeAssignValue dateTime = makeWsAttributeAssignValueTime();

        List<String> attributes = new ArrayList<>();

//        //in include group not self opted
        optInResults = groupingsService.optIn(users.get(1).getUsername(), GROUPING_0_PATH);

        assertTrue(optInResults.get(0).getResultCode().startsWith(SUCCESS));
        assertTrue(optInResults.get(1).getResultCode().startsWith(SUCCESS));
        assertTrue(optInResults.get(2).getResultCode().startsWith(SUCCESS));

        //TODO make a mock database
        //not in include group
//        given(gf.makeWsHasMemberResults(GROUPING_0_INCLUDE_PATH, users.get(1).getUsername())).willReturn(notMemberResults());
//
//        optInResults = groupingsService.optIn(users.get(1).getUsername(), GROUPING_0_PATH);
//
//        assertTrue(optInResults.get(0).getResultCode().startsWith(SUCCESS));
//        assertTrue(optInResults.get(1).getResultCode().startsWith(SUCCESS));
//        assertTrue(optInResults.get(2).getResultCode().startsWith(SUCCESS));


    }

    @Test
    public void optOutTest() {

        //opt in Permission for include group false
        List<GroupingsServiceResult> optInResults = groupingsService.optOut(users.get(1).getUsername(), GROUPING_0_PATH);

        assertTrue(optInResults.get(0).getResultCode().startsWith(FAILURE));

        //opt in Permission for include group true
        WsAttributeAssignValue dateTime = makeWsAttributeAssignValueTime();

        List<String> attributes = new ArrayList<>();

        optInResults = groupingsService.optOut(users.get(1).getUsername(), GROUPING_0_PATH);

        assertTrue(optInResults.get(0).getResultCode().startsWith(SUCCESS));
        assertTrue(optInResults.get(1).getResultCode().startsWith(SUCCESS));
        assertTrue(optInResults.get(2).getResultCode().startsWith(SUCCESS));

        //TODO make a mock database

        //not in include group
        optInResults = groupingsService.optOut(users.get(1).getUsername(), GROUPING_0_PATH);

        assertTrue(optInResults.get(0).getResultCode().startsWith(SUCCESS));
        assertTrue(optInResults.get(1).getResultCode().startsWith(SUCCESS));
        assertTrue(optInResults.get(2).getResultCode().startsWith(SUCCESS));

    }

    @Test
    public void cancelOptInTest() {
        Grouping grouping = groupingRepository.findByPath(GROUPING_1_PATH);

        //not in group
        List<GroupingsServiceResult> cancelOptInResults = groupingsService.cancelOptIn(GROUPING_0_PATH, users.get(2).getUsername());
        assertTrue(cancelOptInResults.get(0).getResultCode().startsWith(SUCCESS));

        //in group but not self opted
        cancelOptInResults = groupingsService.cancelOptIn(GROUPING_0_PATH, users.get(5).getUsername());
        assertTrue(cancelOptInResults.get(0).getResultCode().startsWith(FAILURE));

        //in group and self opted
        Person person = personRepository.findByUsername(users.get(5).getUsername());
        Group group = groupRepository.findByPath(GROUPING_0_INCLUDE_PATH);
        Membership membership = membershipRepository.findByPersonAndGroup(person, group);
        membership.setSelfOpted(true);
        membershipRepository.save(membership);

        cancelOptInResults = groupingsService.cancelOptIn(GROUPING_0_PATH, users.get(5).getUsername());
        assertTrue(cancelOptInResults.get(0).getResultCode().startsWith(SUCCESS));
        assertTrue(cancelOptInResults.get(1).getResultCode().startsWith(SUCCESS));
        assertTrue(cancelOptInResults.get(2).getResultCode().startsWith(SUCCESS));

    }

    @Test
    public void cancelOptOutTest() {
        //not in group
        List<GroupingsServiceResult> cancelOptOutResults = groupingsService.cancelOptOut(GROUPING_0_PATH, users.get(1).getUsername());
        assertTrue(cancelOptOutResults.get(0).getResultCode().startsWith(SUCCESS));

        //in group but not self opted
        cancelOptOutResults = groupingsService.cancelOptOut(GROUPING_0_PATH, users.get(2).getUsername());

        assertTrue(cancelOptOutResults.get(0).getResultCode().startsWith(FAILURE));

        //in group and self opted
        Person person = personRepository.findByUsername(users.get(2).getUsername());
        Group group = groupRepository.findByPath(GROUPING_1_EXCLUDE_PATH);
        Membership membership = membershipRepository.findByPersonAndGroup(person, group);
        membership.setSelfOpted(true);
        membershipRepository.save(membership);

        cancelOptOutResults = groupingsService.cancelOptOut(GROUPING_1_PATH, users.get(2).getUsername());
        assertTrue(cancelOptOutResults.get(0).getResultCode().startsWith(SUCCESS));
        assertTrue(cancelOptOutResults.get(1).getResultCode().startsWith(SUCCESS));
        assertTrue(cancelOptOutResults.get(2).getResultCode().startsWith(SUCCESS));
    }

    @Test
    public void optOutPermissionTest() {

        boolean permission = groupingsService.optOutPermission(GROUPING_0_PATH);

        assertEquals(false, permission);

        permission = groupingsService.optOutPermission(GROUPING_1_PATH);

        assertEquals(true, permission);

    }

    @Test
    public void optInPermissionTest() {

        boolean permission = groupingsService.optInPermission(GROUPING_0_PATH);

        assertEquals(true, permission);

        permission = groupingsService.optInPermission(GROUPING_2_PATH);

        assertEquals(false, permission);
    }

    @Test
    public void groupHasAttributeTest() {

        //group does not have the attribute
        boolean hasAttribute = groupingsService.groupHasAttribute(GROUPING_0_PATH, OPT_OUT);
        assertFalse(hasAttribute);

        //group has the attribute
        hasAttribute = groupingsService.groupHasAttribute(GROUPING_0_PATH, OPT_IN);
        assertTrue(hasAttribute);
    }

    @Test
    //todo
    public void groupingsInTest() {
        String username = "username2";
        Iterable<Group> groupsIn = groupRepository.findByMembersUsername(username);
        List<String> groupPaths = new ArrayList<>();

        for (Group group : groupsIn) {
            groupPaths.add(group.getPath());
        }

        List<Grouping> groupingsIn = groupingsService.groupingsIn(groupPaths);

        for (int i = 0; i < groupingsIn.size(); i++) {
            assertTrue(groupingsIn.get(i).getPath().equals(PATH_ROOT + i));
        }
    }

    @Test
    public void hasListservTest() {

        boolean groupingHasListserv = groupingsService.hasListserv(GROUPING_0_PATH);

        assertEquals(false, groupingHasListserv);

        groupingHasListserv = groupingsService.hasListserv(GROUPING_3_PATH);

        assertEquals(true, groupingHasListserv);
    }

    @Test
    //todo
    public void groupingsOwnedTest() {
        Iterable<Group> groupsIn = groupRepository.findByMembersUsername(users.get(0).getUsername());
        List<String> attributes = new ArrayList<>();
        List<String> groupPaths = new ArrayList<>();

        for (Group group : groupsIn) {
            groupPaths.add(group.getPath());
        }

        List<String> ownerGroups = groupPaths
                .stream()
                .filter(groupPath -> groupPath.endsWith(OWNERS))
                .map(groupPath -> groupPath.substring(0, groupPath.length() - OWNERS.length()))
                .collect(Collectors.toList());

//        given(gf.makeWsGetAttributeAssignmentsResultsTrio(ASSIGN_TYPE_GROUP, TRIO, ownerGroups))
//                .willReturn(makeWsGetAttributeAssignmentsResultsForTrios());
//
//        for (String groups : ownerGroups) {
//            given(gf.makeWsGetAttributeAssignmentsResultsForGroup(ASSIGN_TYPE_GROUP, groups))
//                    .willReturn(makeWsGetAttributeAssignmentsResults(attributes));
//        }

        List<Grouping> groupingsOwned = groupingsService.groupingsOwned(groupPaths);

        for (int i = 0; i < groupingsOwned.size(); i++) {
            assertTrue(groupingsOwned.get(i).getPath().equals(PATH_ROOT + i));
        }
    }

    @Test
    public void groupingsOptedIntoTest() {
//todo
    }

    @Test
    public void groupingsOptedOutOfTest() {
//todo
    }

    @Test
    public void groupingsOptedTest() {
//todo
    }

    @Test
    public void adminListsTest() {
        //todo
        Iterable<Grouping> groupings = groupingRepository.findAll();
        List<String> groupingPaths = new ArrayList<>();
        for (Grouping grouping : groupings) {
            groupingPaths.add(grouping.getPath());
        }
        List<String> attributes = new ArrayList<>();

//        given(gf.makeWsHasMemberResults(GROUPING_ADMINS, ADMIN_USER)).willReturn(hasMemberResults(GROUPING_ADMINS, ADMIN_USER));
//        given(gf.makeWsHasMemberResults(GROUPING_ADMINS, users.get(1).getUsername())).willReturn(hasMemberResults(GROUPING_ADMINS, users.get(1).getUsername()));
//        given(gf.makeWsHasMemberResults(GROUPING_APPS, ADMIN_USER)).willReturn(hasMemberResults(GROUPING_APPS, ADMIN_USER));
//        given(gf.makeWsHasMemberResults(GROUPING_APPS, users.get(1).getUsername())).willReturn(hasMemberResults(GROUPING_APPS, users.get(1).getUsername()));
//
//        given(gf.makeWsGetAttributeAssignmentsResultsTrio(ASSIGN_TYPE_GROUP, TRIO))
//                .willReturn(makeWsGetAttributeAssignmentsResultsForTrios());
//
//        given(gf.makeWsGetMembersResults(SUBJECT_ATTRIBUTE_NAME_UID, ADMIN_LOOKUP, GROUPING_ADMINS))
//                .willReturn(makeWsGetMembersResultsDB(GROUPING_ADMINS));
//
//        given(gf.makeWsSubjectLookup(ADMIN_USER)).willReturn(ADMIN_LOOKUP);
//
//        for (String groupingPath : groupingPaths) {
//            given(gf.makeWsGetAttributeAssignmentsResultsForGroup(ASSIGN_TYPE_GROUP, groupingPath))
//                    .willReturn(makeWsGetAttributeAssignmentsResults(attributes));
//        }

        AdminListsHolder adminListsHolder = groupingsService.adminLists(ADMIN_USER);
        AdminListsHolder emptyAdminListHolder = groupingsService.adminLists(users.get(1).getUsername());


        assertEquals(adminListsHolder.getAllGroupings().size(), 5);
        assertEquals(adminListsHolder.getAdminGroup().getMembers().size(), 1);

        assertEquals(emptyAdminListHolder.getAllGroupings().size(), 0);
        assertEquals(emptyAdminListHolder.getAdminGroup().getMembers().size(), 0);
    }

    @Test
    public void groupingsToOptOutOfTest() {

//todo
    }

    @Test
    public void groupingsToOptIntoTest() {
//todo
    }

    @Test
    public void addSelfOptedTest() {
//todo
    }

    @Test
    public void checkSelfOptedTest() {
        Grouping grouping = groupingRepository.findByPath(GROUPING_0_PATH);

        //user is not in group
        boolean selfOpted = groupingsService.checkSelfOpted(GROUPING_0_INCLUDE_PATH, users.get(2).getUsername());
        assertFalse(selfOpted);

        //user has not self opted
        selfOpted = groupingsService.checkSelfOpted(GROUPING_0_INCLUDE_PATH, users.get(5).getUsername());
        assertFalse(selfOpted);

        //user has self opted
        Person person = personRepository.findByUsername(users.get(5).getUsername());
        Group group = groupRepository.findByPath(GROUPING_0_INCLUDE_PATH);
        Membership membership = membershipRepository.findByPersonAndGroup(person, group);
        membership.setSelfOpted(true);
        membershipRepository.save(membership);

        selfOpted = groupingsService.checkSelfOpted(GROUPING_0_INCLUDE_PATH, users.get(5).getUsername());
        assertTrue(selfOpted);
    }

    @Test
    public void inGroupTest() {
        assertFalse(groupingsService.inGroup(GROUPING_0_PATH, users.get(2).getUsername()));
        assertTrue(groupingsService.inGroup(GROUPING_0_PATH, users.get(5).getUsername()));
    }

    @Test
    public void isOwnerTest() {
        assertFalse(groupingsService.isOwner(GROUPING_0_PATH, users.get(1).getUsername()));
        assertTrue(groupingsService.isOwner(GROUPING_0_PATH, users.get(0).getUsername()));

    }

    @Test
    public void isAdminTest() {
        assertFalse(groupingsService.isAdmin(users.get(1).getUsername()));
        assertTrue(groupingsService.isAdmin(ADMIN_USER));
    }

    @Test
    public void removeSelfOptedTest() {

//todo
    }

    @Test
    public void groupOptOutPermissionTest() {

//todo
    }

    @Test
    public void groupOptInPermissionTest() {

//todo
    }

    @Test
    public void updateLastModifiedTest() {

//todo
    }

    @Test
    public void assignMembershipAttributesTest() {

//todo
    }

    @Test
    public void getMembershipAttributesTest() {

//todo
    }

    @Test
    public void assignGroupAttributesTest() {

//todo
    }

    @Test
    public void attributeAssignmentsResultsTest() {

//todo
    }

    @Test
    public void getGrouperPrivilegeTest() {

//todo
    }

    @Test
    public void assignGrouperPrivilegeTest() {

//todo
    }

    @Test
    public void membershipsResultsTest() {

//todo
    }

    @Test
    public void addMemberAsTest() {
        //todo make database automatically update composite group
        Grouping grouping = groupingRepository.findByPath(GROUPING_1_PATH);
        assertFalse(grouping.getComposite().getMembers().contains(users.get(3)));

        groupingsService.addMemberAs(users.get(0).getUsername(), GROUPING_1_INCLUDE_PATH, users.get(3).getUsername());
        grouping = groupingRepository.findByPath(GROUPING_1_PATH);
        assertTrue(grouping.getComposite().getMembers().contains(users.get(3)));
    }

    @Test
    public void deleteMemberAsTest() {

//todo
    }

    @Test
    public void deleteMemberTest() {

//todo
    }

    @Test
    public void getMembersTest() {

//todo
    }

    @Test
    public void extractGroupingsTest() {

//todo
    }

    @Test
    public void getGroupPathsTest() {

//todo
    }

    @Test
    public void setGroupingAttributesTest() {

//todo
    }

    @Test
    public void parentGroupingPathTest() {

//todo
    }

    @Test
    public void extractGroupPathsTest() {

//todo
    }

    @Test
    public void changeGroupAttributeStatusTest() {

//todo
    }

    /////////////////////////////////////////////////////
    //factory methods
    //////////////////////////////////////////////////////////


    private WsHasMemberResults hasMemberResults(String groupPath, String username) {
        WsHasMemberResults isMemberResults = new WsHasMemberResults();
        WsHasMemberResult isMemberResult = new WsHasMemberResult();
        WsResultMeta isMemberMeta = new WsResultMeta();

        Group group = groupRepository.findByPath(groupPath);

        if (group.getUsernames().contains(username)) {
            isMemberMeta.setResultCode(IS_MEMBER);
        } else {
            isMemberMeta.setResultCode("not member");
        }

        isMemberResult.setResultMetadata(isMemberMeta);
        isMemberResults.setResults(new WsHasMemberResult[]{isMemberResult});

        return isMemberResults;

    }

    private WsHasMemberResults isMemberResults() {
        WsHasMemberResults isMemberResults = new WsHasMemberResults();
        WsHasMemberResult isMemberResult = new WsHasMemberResult();
        WsResultMeta isMemberMeta = new WsResultMeta();
        isMemberMeta.setResultCode(IS_MEMBER);
        isMemberResult.setResultMetadata(isMemberMeta);
        isMemberResults.setResults(new WsHasMemberResult[]{isMemberResult});

        return isMemberResults;
    }

    private WsHasMemberResults notMemberResults() {
        WsHasMemberResults notMemberResults = new WsHasMemberResults();
        WsHasMemberResult notMemberResult = new WsHasMemberResult();
        WsResultMeta notMemberMeta = new WsResultMeta();
        notMemberMeta.setResultCode("not member");
        notMemberResult.setResultMetadata(notMemberMeta);
        notMemberResults.setResults(new WsHasMemberResult[]{notMemberResult});

        return notMemberResults;
    }

    private WsAddMemberResults addMemberResults(String groupPath, String username) {
        WsAddMemberResults addMemberResults = new WsAddMemberResults();
        WsResultMeta resultMeta = new WsResultMeta();

        try {
            Grouping grouping = groupingRepository.findByIncludePathOrExcludePathOrCompositePathOrOwnersPath(groupPath, groupPath, groupPath, groupPath);

            Person person = personRepository.findByUsername(username);

            Group group = groupRepository.findByPath(groupPath);

            if (!group.getMembers().contains(person)) {
                group.getMembers().add(person);

                groupRepository.save(group);

                Grouping newGrouping = databaseSetup.makeGrouping(
                        grouping.getPath(),
                        grouping.getBasis(),
                        grouping.getExclude(),
                        grouping.getInclude(),
                        grouping.getOwners(),
                        grouping.isListservOn(),
                        grouping.isOptInOn(),
                        grouping.isOptOutOn());

                groupingRepository.save(newGrouping);
            }

            resultMeta.setResultCode(SUCCESS);
        } catch (Exception e) {

            resultMeta.setResultCode(FAILURE);
        }

        addMemberResults.setResultMetadata(resultMeta);

        return addMemberResults;
    }

    private WsDeleteMemberResults deleteMemberResultsSuccess(String groupPath, String username) {
        WsDeleteMemberResults deleteMemberResults = new WsDeleteMemberResults();
        WsResultMeta resultMeta = new WsResultMeta();
        resultMeta.setResultCode(SUCCESS);
        deleteMemberResults.setResultMetadata(resultMeta);

        Person person = personRepository.findByUsername(username);

        Group group = groupRepository.findByPath(groupPath);
        group.getMembers().remove(person);

        groupRepository.save(group);

        Grouping grouping = groupingRepository.findByIncludePathOrExcludePathOrCompositePathOrOwnersPath(groupPath, groupPath, groupPath, groupPath);

        //makes sure composite is updated
        Grouping newGrouping = databaseSetup.makeGrouping(
                grouping.getPath(),
                grouping.getBasis(),
                grouping.getExclude(),
                grouping.getInclude(),
                grouping.getOwners(),
                grouping.isListservOn(),
                grouping.isOptInOn(),
                grouping.isOptOutOn());

        groupingRepository.save(newGrouping);

        return deleteMemberResults;
    }

    private WsGetAttributeAssignmentsResults makeWsGetAttributeAssignmentsResultsForGrouping(String groupingPath) {
        WsGetAttributeAssignmentsResults getAttributeAssignmentsResults = new WsGetAttributeAssignmentsResults();
        List<WsAttributeDefName> attributeDefNames = new ArrayList<>();
        List<WsAttributeAssign> attributeAssigns = new ArrayList<>();
        List<String> attributes = new ArrayList<>();

        Grouping grouping = groupingRepository.findByPath(groupingPath);

        if (grouping.isListservOn()) {
            attributes.add(LISTSERV);
        }
        if (grouping.isOptInOn()) {
            attributes.add(OPT_IN);
        }
        if (grouping.isOptOutOn()) {
            attributes.add(OPT_OUT);
        }

        if (attributes.size() > 0) {
            for (String attribute : attributes) {
                WsAttributeDefName attributeDefName = new WsAttributeDefName();
                attributeDefName.setName(attribute);
                attributeDefNames.add(attributeDefName);

                WsAttributeAssign attributeAssign = new WsAttributeAssign();
                attributeAssign.setAttributeDefNameName(attribute);
                attributeAssigns.add(attributeAssign);
            }
            getAttributeAssignmentsResults.setWsAttributeDefNames(attributeDefNames.toArray(new WsAttributeDefName[attributeDefNames.size()]));
            getAttributeAssignmentsResults.setWsAttributeAssigns(attributeAssigns.toArray(new WsAttributeAssign[attributeDefNames.size()]));

        }
        return getAttributeAssignmentsResults;

    }

    private WsGetAttributeAssignmentsResults makeWsGetAttributeAssignmentsResults(List<String> attributes) {
        //todo update for database
        WsGetAttributeAssignmentsResults getAttributeAssignmentsResults = new WsGetAttributeAssignmentsResults();
        List<WsAttributeDefName> attributeDefNames = new ArrayList<>();
        List<WsAttributeAssign> attributeAssigns = new ArrayList<>();

        if (attributes != null) {
            for (String attribute : attributes) {
                WsAttributeDefName attributeDefName = new WsAttributeDefName();
                attributeDefName.setName(attribute);
                attributeDefNames.add(attributeDefName);

                WsAttributeAssign attributeAssign = new WsAttributeAssign();
                attributeAssign.setAttributeDefNameName(attribute);
                attributeAssigns.add(attributeAssign);
            }
            getAttributeAssignmentsResults.setWsAttributeDefNames(attributeDefNames.toArray(new WsAttributeDefName[attributeDefNames.size()]));
            getAttributeAssignmentsResults.setWsAttributeAssigns(attributeAssigns.toArray(new WsAttributeAssign[attributeDefNames.size()]));

        }
        return getAttributeAssignmentsResults;

    }

    private WsGetAttributeAssignmentsResults makeWsGetAttributeAssignmentsResultsForTrios() {
        //todo change other methods to get trios for groupings owned
        WsGetAttributeAssignmentsResults getAttributeAssignmentsResults = new WsGetAttributeAssignmentsResults();

        WsAttributeDefName attributeDefName = new WsAttributeDefName();
        attributeDefName.setName(TRIO);

        List<WsAttributeAssign> attributeAssigns = new ArrayList<>();

        Iterable<Grouping> groupings = groupingRepository.findAll();
        List<String> groupingPaths = new ArrayList<>();
        for (Grouping grouping : groupings) {
            groupingPaths.add(grouping.getPath());
        }
        List<WsGroup> groupsList = new ArrayList<>();
        for (String groupPath : groupingPaths) {
            WsGroup group = new WsGroup();
            group.setName(groupPath);
            groupsList.add(group);
        }

        WsGroup[] groups = groupsList.toArray(new WsGroup[groupsList.size()]);

        for (String group : groupingPaths) {

            WsAttributeAssign attributeAssign = new WsAttributeAssign();
            attributeAssign.setAttributeDefNameName(TRIO);
            attributeAssign.setOwnerGroupName(group);
            attributeAssigns.add(attributeAssign);
        }
        getAttributeAssignmentsResults.setWsAttributeDefNames(new WsAttributeDefName[]{attributeDefName});
        getAttributeAssignmentsResults.setWsAttributeAssigns(attributeAssigns.toArray(new WsAttributeAssign[attributeAssigns.size()]));
        getAttributeAssignmentsResults.setWsGroups(groups);

        return getAttributeAssignmentsResults;
    }

    private WsAssignAttributesResults makeWsAssignAttributesResults(String resultCode) {
        WsAssignAttributesResults assignAttributesResults = new WsAssignAttributesResults();
        WsResultMeta resultMeta = new WsResultMeta();
        resultMeta.setResultCode(resultCode);
        assignAttributesResults.setResultMetadata(resultMeta);

        return assignAttributesResults;
    }

    private WsAssignAttributesResults makeWsAssignAttributesResultsForGrouping(String groupingPath, String attribute, boolean isOn) {
        WsAssignAttributesResults assignAttributesResults = new WsAssignAttributesResults();
        WsResultMeta resultMeta = new WsResultMeta();
        resultMeta.setResultCode(SUCCESS);
        assignAttributesResults.setResultMetadata(resultMeta);

        Grouping grouping = groupingRepository.findByPath(groupingPath);

        if (attribute.equals(LISTSERV)) {
            grouping.setListservOn(isOn);
        } else if (attribute.equals(OPT_IN)) {
            grouping.setOptInOn(isOn);
        } else if (attribute.equals(OPT_OUT)) {
            grouping.setOptOutOn(isOn);
        }

        groupingRepository.save(grouping);

        return assignAttributesResults;
    }


    private WsAssignGrouperPrivilegesLiteResult makeWsAssignGrouperPrivilegesLiteResult(String resultCode) {
        //todo update for database
        WsAssignGrouperPrivilegesLiteResult agplr = new WsAssignGrouperPrivilegesLiteResult();
        WsResultMeta resultMeta = new WsResultMeta();
        resultMeta.setResultCode(resultCode);
        agplr.setResultMetadata(resultMeta);

        return agplr;
    }

    private WsGetMembersResults makeWsGetMembersResults(String groupPath) {
        //todo update for database
        List<WsSubject> subjects = new ArrayList<>();
        List<Person> persons = groupRepository.findByPath(groupPath).getMembers();
        for (Person person : persons) {
            WsSubject subject = new WsSubject();
            subject.setName(person.getName());
            subject.setId(person.getUuid());
            subject.setAttributeValues(new String[]{person.getUsername()});

            subjects.add(subject);

        }

        WsGetMembersResults getMembersResults = new WsGetMembersResults();
        WsGetMembersResult getMembersResult = new WsGetMembersResult();
        getMembersResult.setWsSubjects(subjects.toArray(new WsSubject[subjects.size()]));

        WsGetMembersResult[] membersResults = new WsGetMembersResult[]{getMembersResult};
        getMembersResults.setResults(membersResults);


        return getMembersResults;
    }

    private WsGetMembersResults makeWsGetMembersResultsDB(String groupPath) {
        List<WsSubject> subjects = new ArrayList<>();
        Group group = groupRepository.findByPath(groupPath);

        List<Person> members = group.getMembers();
        for (Person member : members) {
            WsSubject subject = new WsSubject();
            subject.setName(member.getName());
            subject.setId(member.getUuid());
            subject.setAttributeValues(new String[]{member.getUsername()});

            subjects.add(subject);
        }

        WsGetMembersResults getMembersResults = new WsGetMembersResults();
        WsGetMembersResult getMembersResult = new WsGetMembersResult();
        getMembersResult.setWsSubjects(subjects.toArray(new WsSubject[subjects.size()]));

        WsGetMembersResult[] membersResults = new WsGetMembersResult[]{getMembersResult};
        getMembersResults.setResults(membersResults);

        return getMembersResults;
    }

    private WsAttributeAssignValue makeWsAttributeAssignValueTime() {
        WsAttributeAssignValue attributeAssignValue = new WsAttributeAssignValue();
        attributeAssignValue.setValueSystem("time");
        return attributeAssignValue;
    }

    private WsGetGrouperPrivilegesLiteResult makeWsGetGrouperPrivilegesLiteResult(boolean success) {
        //todo update for database

        WsGetGrouperPrivilegesLiteResult gplr = new WsGetGrouperPrivilegesLiteResult();
        WsResultMeta resultMetadata = new WsResultMeta();
        if (success) {
            resultMetadata.setResultCode(SUCCESS_ALLOWED);
        } else {
            resultMetadata.setResultCode(FAILURE);
        }
        gplr.setResultMetadata(resultMetadata);

        return gplr;
    }

    private WsGetMembershipsResults makeWsGetMembershipsResults() {
        //todo update for database
        WsGetMembershipsResults membershipsResults = new WsGetMembershipsResults();
        WsMembership membership = new WsMembership();
        membership.setMembershipId("1234");
        WsMembership[] memberships = new WsMembership[]{membership};
        membershipsResults.setWsMemberships(memberships);

        return membershipsResults;

    }

}