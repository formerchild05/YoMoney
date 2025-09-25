import { useState } from "react";

export default function Hello() {
    const [count, setCount] = useState(0);
    function handle() {

        setCount(count + 2);
        console.log(count);
    }
    return (<div>
        <button onClick={handle}>{count} times</button>
    </div>);
}