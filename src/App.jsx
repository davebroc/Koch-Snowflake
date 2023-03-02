import { useEffect, useRef, useState } from 'react'
import './App.css'
import Canvas from './Canvas'

function App() {
  const [order, setOrder] = useState(1)
  const [suffix, setSuffix] = useState("st")
  // const canvasRef = document.getElementById("mainCanvas")
  // const memoryCanvas = document.createElement("canvas");
  // const memoryCanvasContext = memoryCanvas.getContext("2d");
  // const ctx = canvasRef.getContext("2d");

  useEffect(() => {
    let orderString = order.toString()
    switch (orderString.charAt(orderString.length - 1)) {
      case "1":
        setSuffix("st")
        break;
      case "2":
        setSuffix("nd")
        break;
      case "3":
        setSuffix("rd")
        break;

      default:
        setSuffix("th")
        break;
    }

  }, [order])


  return (
    <div className="flex flex-col h-screen justify-between items-center p-12">
      <section className="w-full h-full sm:h-[30rem] sm:w-[30rem] flex justify-center items-center">
        <canvas id="mainCanvas" className='border border-black ' />
        <Canvas order={order} />
        {/* <svg id="triangle" viewBox="0 0 100 100">
          <polygon className="fill-none stroke-black stroke-2" points={points} />
        </svg>*/}
      </section>
      <section className="flex flex-col items-center gap-2">

        <p>Koch Snowflake of the {order}{suffix} order</p>
        <input type="range" min="1" max="10" value={order} onChange={(e) => setOrder(e.target.value)} />
      </section>
    </div>
  )
}

export default App
// const [points, setPoints] = useState("50 31,100 100,0 100")
// const canvasRef = useRef(null)
// useEffect(() => {
//   let orderString = order.toString()
//   switch (orderString.charAt(orderString.length - 1)) {
//     case "1":
//       setSuffix("st")
//       break;
//     case "2":
//       setSuffix("nd")
//       break;
//     case "3":
//       setSuffix("rd")
//       break;

//     default:
//       setSuffix("th")
//       break;
//   }
//   updateSnowflake();

// }, [order])

// // function updateSnowflake(num) {
// //   if (num == order)
// //     returnss
// //   return updateSnowflake(num + 1)
// // }
// function updateSnowflake() {
//   let coords = points.split(",");
//   coords = coords.map(coord => {
//     let arr = coord.split(" ")
//     return { x: arr[0], y: arr[1] }
//   })


//   let length = Math.sqrt(Math.pow(parseInt(coords[0].x), 2) + Math.pow(100 - parseInt(coords[0].y), 2))
//   console.log(length);
//   let p = "50 31,100 100,0 100"
//   const initialHeight = 100 - 31;
//   let height = Math.sin(Math.PI / 3) * length;
//   let x1 = Math.cos(Math.PI / 3) * (1 / 3) * length
//   let y1 = initialHeight - (Math.sin(Math.PI / 3) * (1 / 3) * length)
//   let x2 = 2 * x1
//   let y2 = initialHeight - (2 * (Math.sin(Math.PI / 3) * (1 / 3) * length))
//   let zx = x1 - (5 / 4) * Math.pow(length, 2)
//   let zy = y2 - (5 / 4) * Math.pow(length, 2)

//   for (let i = 1; i < order; i++) {
//     p += "," + x1.toString() + " " + y1.toString() +
//       "," + x2.toString() + " " + y2.toString() +
//       "," + zx.toString() + " " + zy.toString()
//   }
//   setPoints(p)

// }
