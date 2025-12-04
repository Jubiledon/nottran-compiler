package uk.ac.nott.cs.comp3012.coursework.types;

import java.util.List;

public sealed interface Type {

    enum BaseType implements Type {
        Number, Logical
    }

    record ArrayType(Type base, List<Integer> dims) implements Type {

    }
}
