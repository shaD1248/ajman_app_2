package ajman.shd.app1.models.structure

class LineLoading(private var areaLoading: AreaLoading, private var compositeJoist: CompositeJoist) {
    init {
        areaLoading.calculateSelfWeight(compositeJoist)
    }
    var qu: Double
        get() = areaLoading.wu * compositeJoist.b
        set(value) {}
}