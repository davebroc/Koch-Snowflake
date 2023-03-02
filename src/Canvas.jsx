import React, { useEffect, useRef } from 'react';

function Canvas(props) {
    const canvasRef = useRef(null);

    useEffect(() => {
        const canvas = canvasRef.current;
        const context = canvas.getContext('2d');

        function resizeCanvas() {
            canvas.width = canvas.parentNode.clientWidth;
            canvas.height = canvas.parentNode.clientHeight;
            draw();
        }

        window.addEventListener('resize', resizeCanvas);
        resizeCanvas();

        function draw() {
            context.beginPath();
            context.moveTo(0, 100);
            context.lineTo(50, 0);
            context.lineTo(100, 100);
            context.closePath();
            context.stroke();
        }


        return () => {
            window.removeEventListener('resize', resizeCanvas);
        };
    }, []);
    console.log(props.order);

    return <canvas ref={canvasRef} />;
}

export default Canvas;