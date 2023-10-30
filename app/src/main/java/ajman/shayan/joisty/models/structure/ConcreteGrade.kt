package ajman.shayan.joisty.models.structure

enum class ConcreteGrade {
    C20;

    fun getConcrete(): Concrete = when (this) {
        C20 -> Concrete(ajman.shayan.joisty.models.structure.C20)
    }
}
