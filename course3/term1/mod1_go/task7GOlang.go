package main

import (
	"fmt"
	"time"
)

type Tunnel struct {
	number int
    trainsOnLeft int
	trainsOnRight int
}

func (tunnel Tunnel) Work(OtherTunnel * Tunnel) {
	for tunnel.trainsOnLeft > 0 || tunnel.trainsOnRight > 0 {
		if tunnel.trainsOnLeft > 0 {
			tunnel.trainsOnLeft--
			fmt.Println("Tunnel ", tunnel.number, ": train entered left side")
			if tunnel.trainsOnLeft > 0 {
				fmt.Println("Tunnel ", tunnel.number, ": train went to other's tunnel left side")
				OtherTunnel.trainsOnLeft++
				tunnel.trainsOnLeft--
			}
			time.Sleep(time.Second)
		}
		if tunnel.trainsOnRight > 0 {
			tunnel.trainsOnRight--
			fmt.Println("Tunnel ", tunnel.number, ": train entered right side")
			if tunnel.trainsOnRight > 0 {
				fmt.Println("Tunnel ", tunnel.number, ": train went to other's tunnel right side")
				OtherTunnel.trainsOnRight++
				tunnel.trainsOnRight--
			}
			time.Sleep(time.Second)
		}
	}
}

func main() {

	var Tunnel1 = Tunnel{number: 1, trainsOnLeft: 5, trainsOnRight: 5}
	var Tunnel2 = Tunnel{number: 2, trainsOnLeft: 5, trainsOnRight: 5}

	go Tunnel1.Work(&Tunnel2)
	Tunnel2.Work(&Tunnel1)
}