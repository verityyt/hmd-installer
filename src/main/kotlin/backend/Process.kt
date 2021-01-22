package backend

abstract class Process {

    abstract var test: Boolean

    abstract var status: Int

    abstract fun run()

}