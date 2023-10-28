package ajman.shd.app1.models.structure

enum class ConcreteGrade {
    C20;

    fun getConcrete(): Concrete = when (this) {
        C20 -> Concrete(ajman.shd.app1.models.structure.C20)
    }
}
