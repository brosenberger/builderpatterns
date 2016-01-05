package at.bro.code.builderpattern;

import org.junit.Assert;
import org.testng.annotations.Test;

import at.bro.code.builderpattern.exceptions.LegException;
import at.bro.code.builderpattern.exceptions.NoPetException;
import at.bro.code.builderpattern.interfaces.Person;

@Test(groups = "unit")
public class BuilderPatternTest {

    @Test
    void testSimplePersonBuilding() {
        final Person susi = NormalPerson.create("Susi")
                // setting age
                .age(12)
                // setting pet
                .pet(BorderColly.create("Collins")).toy("Bone").build()
                // build person
                .build();

        Assert.assertTrue("Collins".equals(susi.getPet().getName()));
    }

    @Test
    void testOverridingPet() {
        final Person daniel = NormalPerson.create("Daniel")
                // setting first pet
                .pet(BorderColly.create("Schocko")).build()
                // now overriding pet
                .pet(Emu.create("Tweety")).build()
                // build person
                .build();

        Assert.assertTrue("Tweety".equals(daniel.getPet().getName()));
    }

    @Test(expectedExceptions = LegException.class)
    void testFailureInPetBuilding() {
        NormalPerson.create("Tom")
                // setting age
                .age(10)
                // setting pet
                .pet(Emu.create("Erwin")).legCount(100).build()
                // build person
                .build();
    }

    @Test(expectedExceptions = NoPetException.class)
    void testNoPetSetBuilding() {
        NormalPerson.create("Hugo").build();
    }

    @Test
    void testGuidedPersonBuilder() {
        PetLover.create("Toni")
                // pets come first for pet lovers!
                .pet(BorderColly.create("Pepper")).build()
                // now the age, but a pet can only be set once!
                .age(10)
                // build person
                .build();

        PetLover.create("Lucy")
                // pets come first for pet lovers!
                .pet(BorderColly.create("Judy")).build()
                // building is also allowed here, but no more pets
                .build();
    }

    @Test
    void testObjectVariationBuilding() {
        final Person august = PetLover.create("August")
                // pet
                .pet(BorderColly.create("Heinz")).build()
                // set age to senior
                .age(70)
                // build person
                .build();

        final Person fritz = PetLover.create("Fritz")
                // pet
                .pet(Emu.create("Franzi")).build()
                // age
                .age(20)
                // build
                .build();

        Assert.assertTrue(august instanceof SeniorPetLover);
        Assert.assertTrue(fritz instanceof PetLover);
        Assert.assertFalse(fritz instanceof SeniorPetLover);
    }
}
