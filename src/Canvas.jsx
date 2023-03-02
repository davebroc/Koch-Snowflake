import React, { useEffect, useRef } from 'react';


import React, { Component } from 'react'
class Canvas extends Component {
    constructor(props) {
        super(props);
        this.canvasRef = React.createRef();
    }
    componentDidMount() {
        const canvas = document.getElementById('mainCanvas');
        const context = canvas.getContext('2d');
    }

    componentDidUpdate() {
        recursiveDraw(props.order);
        // function resizeCanvas() {
        //     canvas.width = canvas.parentNode.clientWidth;
        //     canvas.height = canvas.parentNode.clientHeight;
        // }
        // window.addEventListener('resize', resizeCanvas);
        // resizeCanvas();


        // return () => {
        //     window.removeEventListener('resize', resizeCanvas);
        // };
    }

    recursiveDraw(order) {
        if (order === 0)
            return
        let canvasCenter = { x: canvas.width / 2, y: canvas.height / 2 }

        context.beginPath();
        context.moveTo(canvasCenter.x, canvasCenter.y + 100);
        context.lineTo(canvasCenter.x + 50, canvasCenter.y + 0);
        context.lineTo(canvasCenter.x + 50, canvasCenter.y + 100);
        // context.closePath();
        context.stroke();
        console.log("recusive");

        return recursiveDraw(order - 1)
    }


    render() {
        function getCursorPosition(canvas, event) {
            const rect = canvas.getBoundingClientRect()
            const x = event.clientX - rect.left
            const y = event.clientY - rect.top
            console.log("x: " + x + " y: " + y)
        }
        return (
            <canvas className='border border-black'
                ref={this.canvasRef} />
        )
    }
}


export default Canvas;