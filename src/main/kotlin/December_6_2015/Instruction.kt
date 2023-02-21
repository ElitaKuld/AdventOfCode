package December_6_2015

class Instruction (val action : String, val columnValueA : Int, val rowValueA : Int, val columnValueB : Int, val rowValueB : Int){
    override fun toString(): String {
        return "$action $columnValueA $rowValueA $columnValueB $rowValueB"
    }
}