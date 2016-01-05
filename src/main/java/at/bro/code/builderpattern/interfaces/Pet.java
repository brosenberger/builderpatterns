package at.bro.code.builderpattern.interfaces;

import at.bro.code.builderpattern.PetKind;

public interface Pet {
    String getName();

    PetKind getKind();

    int getLegCount();
}
