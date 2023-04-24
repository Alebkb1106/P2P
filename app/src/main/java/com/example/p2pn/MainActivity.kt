package com.example.p2pn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nodes = mutableListOf<Node>()
        for (i in 0..4) {
            nodes.add(Node(i))
        }

        for (i in 0 until nodes.size) {
            nodes[i].neighbors.add(nodes[(i+1)%nodes.size])
            nodes[i].neighbors.add(nodes[(i-1+nodes.size)%nodes.size])
        }

        nodes[4].send("Hello, world!")
    }

    class Node(val id: Int) {
        val neighbors = mutableListOf<Node>()

        fun send(message: String) {
            neighbors.forEach { it.receive(message) }
        }

        fun receive(message: String) {
            println("Node $id received message: $message")
        }
    }
}